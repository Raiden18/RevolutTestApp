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
        val newRates = LinkedList<CurrencyRate>()
        forEach { currencyRate ->
            if (currencyRate.currencyCode != newSelectedCurrency.getCode()) {
                newRates.add(currencyRate.copy())
            } else {
                val currencyRateForOldSelectedCurrencyValue =
                    if (currencyRate.rate == 0.0) 0.0 else 1 / currencyRate.rate
                val currencyRateForOldSelectedCurrency = CurrencyRate(
                    oldSelectedCurrency.setAmount(0.0),
                    currencyRateForOldSelectedCurrencyValue.round(2)
                )
                newRates.add(currencyRateForOldSelectedCurrency)
            }
        }
        return newRates
    }

    private fun Double.round(places: Int): Double {
        val bigDecimal: BigDecimal = BigDecimal.valueOf(this)
        return bigDecimal.setScale(places, RoundingMode.HALF_UP).toDouble()
    }
}
