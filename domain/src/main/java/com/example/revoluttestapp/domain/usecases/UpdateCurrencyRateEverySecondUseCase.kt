package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.example.revoluttestapp.domain.utils.RxSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class UpdateCurrencyRateEverySecondUseCase(
    private val currencyRepository: CurrencyRepository,
    private val currencyRatesRepository: CurrencyRatesRepository,
    private val rxSchedulers: RxSchedulers
) {
    fun execute(): Completable {
        return Observable.interval(1, TimeUnit.SECONDS, rxSchedulers.computation)
            .switchMapCompletable {
                currencyRepository.getSelectedCurrencyFromMemory()
                    .distinctUntilChanged()
                    .switchMapCompletable { oldSelectedCurrency ->
                        updateCurrencyRates(oldSelectedCurrency)
                    }
            }
    }

    private fun updateCurrencyRates(oldSelectedCurrency: Currency) = currencyRatesRepository.getCurrencyRateFromApiFor(oldSelectedCurrency.getCode())
        .flatMapCompletable { updatedCurrencyRates->
            checkThatSelectedCurrencyWasNotChanged(oldSelectedCurrency)
                .flatMapCompletable { currencyRatesRepository.saveToMemory(updatedCurrencyRates) }
        }

    private fun checkThatSelectedCurrencyWasNotChanged(
        oldSelectedCurrency: Currency)= currencyRepository.getSelectedCurrencyFromMemory()
            .filter { newSelectedCurrency->  oldSelectedCurrency.getCode() == newSelectedCurrency.getCode()}
}
