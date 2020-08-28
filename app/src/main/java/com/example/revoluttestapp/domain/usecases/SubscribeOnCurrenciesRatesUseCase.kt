package com.example.revoluttestapp.domain.usecases

import android.util.Log
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.example.revoluttestapp.domain.utils.RxSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class SubscribeOnCurrenciesRatesUseCase(
    private val currencyRepository: CurrencyRepository,
    private val currencyRatesRepository: CurrencyRatesRepository,
    private val rxSchedulers: RxSchedulers
) {
    fun execute(): Observable<List<CurrencyRate>> {
        return currencyRepository.getCurrentCurrencyFromMemory()
            .map { it.getCode() }
            .distinctUntilChanged()
            .switchMap { currency ->
                Observable.interval(1, TimeUnit.SECONDS, rxSchedulers.computation).flatMap {
                    currencyRatesRepository.getCurrencyRateFromApiFor(currency)
                }
            }
    }
}