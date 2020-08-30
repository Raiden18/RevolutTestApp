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
    private var shouldRun: Boolean = true

    fun execute(): Completable {
        shouldRun = true
        return Observable.interval(1, TimeUnit.SECONDS, rxSchedulers.computation)
            .filter { shouldRun }
            .switchMapCompletable {
                currencyRepository.getSelectedCurrencyFromMemory()
                    .distinctUntilChanged()
                    .switchMapCompletable { oldSelectedCurrency ->
                        updateCurrencyRates(oldSelectedCurrency)
                    }
            }.retry { error -> error is Throwable
            }
    }

    fun unExecute(shouldRun: Boolean): Completable {
        return Completable.fromAction { this.shouldRun = shouldRun }
    }

    private fun updateCurrencyRates(oldSelectedCurrency: Currency) =
        currencyRatesRepository.getCurrencyRateFromApiFor(oldSelectedCurrency.getCode())
            .flatMapCompletable { updatedCurrencyRates ->
                checkThatSelectedCurrencyWasNotChanged(oldSelectedCurrency)
                    .flatMapCompletable { currencyRatesRepository.saveToMemory(updatedCurrencyRates) }
            }

    private fun checkThatSelectedCurrencyWasNotChanged(
        oldSelectedCurrency: Currency
    ) = currencyRepository.getSelectedCurrencyFromMemory()
        .filter { newSelectedCurrency -> oldSelectedCurrency.getCode() == newSelectedCurrency.getCode() }
}
