package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Completable
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class UpdateCurrencySelectedCurrencyAndRates(
    private val currencyRepository: CurrencyRepository,
    private val currencyRatesRepository: CurrencyRatesRepository
) {
    fun execute(newSelectedCurrency: Currency): Completable {
        return currencyRepository.getSelectedCurrencyFromMemory()
            .switchMapCompletable { oldSelectedCurrency ->
                currencyRatesRepository.getCurrencyRateFromMemory()
                    .take(1)
                    .map {
                        it.removeNewSelectedCurrencyFromRatesAndAddThereOldSelectedCurrency(
                            newSelectedCurrency,
                            oldSelectedCurrency
                        )
                    }.flatMapCompletable {
                        currencyRepository.saveToMemoryCurrentCurrency(newSelectedCurrency)
                            .andThen(currencyRatesRepository.saveToMemory(it))
                    }
            }
    }

    private fun List<CurrencyRate>.removeNewSelectedCurrencyFromRatesAndAddThereOldSelectedCurrency(
        newSelectedCurrency: Currency,
        oldSelectedCurrency: Currency
    ): List<CurrencyRate> {
        return map {
            if (it.currencyCode != newSelectedCurrency.code) {
                it.copy()
            } else {
                it.calculateCurrencyRateFrom(oldSelectedCurrency)
            }
        }
    }


}
