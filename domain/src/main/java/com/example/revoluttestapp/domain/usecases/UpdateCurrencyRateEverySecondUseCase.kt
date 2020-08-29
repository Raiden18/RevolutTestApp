package com.example.revoluttestapp.domain.usecases

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
                currencyRepository.getCurrentCurrencyFromMemory()
                    .map { it.getCode() }
                    .distinctUntilChanged()
                    .switchMapCompletable { currency ->
                        currencyRatesRepository.getCurrencyRateFromApiFor(currency)
                            .flatMapCompletable { currencyRatesRepository.saveToMemory(it) }
                    }
            }


    }
}