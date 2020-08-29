package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.example.revoluttestapp.domain.utils.Logger
import io.reactivex.rxjava3.core.Completable
import java.util.*

class UpdateCurrencySelectedCurrencyAndRates(
    private val currencyRepository: CurrencyRepository,
    private val currencyRatesRepository: CurrencyRatesRepository,
    private val logger: Logger
) {
    fun execute(newSelectedCurrency: Currency): Completable {
        return currencyRepository.getSelectedCurrencyFromMemory()
            .switchMapCompletable { oldSelectedCurrency ->
                currencyRatesRepository.getCurrencyRateFromMemory()
                    .take(1)
                    .map { savedRates ->
                        removeNewSelectedCurrencyFromRatesAndAddThereOldSelectedCurrency(savedRates, newSelectedCurrency, oldSelectedCurrency)
                    }
                    .flatMapCompletable {
                        currencyRepository.saveToMemoryCurrentCurrency(newSelectedCurrency)
                            .andThen(currencyRatesRepository.saveToMemory(it))
                    }

            }
    }

    private fun removeNewSelectedCurrencyFromRatesAndAddThereOldSelectedCurrency(
        savedRates: List<CurrencyRate>,
        newSelectedCurrency: Currency,
        oldSelectedCurrency: Currency
    ): List<CurrencyRate>{
        val newRates = LinkedList<CurrencyRate>()
        savedRates.forEach { currencyRate ->
            if (currencyRate.currency.getCode() != newSelectedCurrency.getCode()) {
                newRates.add(currencyRate.copy())
            } else {
                logger.logText("HUI", currencyRate.rate.toString())
                val currencyRateForOldSelectedCurrecyValue = 1 / currencyRate.rate
                val currencyRateForOldSelectedCurrnecy = CurrencyRate(
                    oldSelectedCurrency.setAmount(0.0),
                    currencyRateForOldSelectedCurrecyValue
                )
                newRates.add(currencyRateForOldSelectedCurrnecy)
            }
        }
        return newRates
    }
}