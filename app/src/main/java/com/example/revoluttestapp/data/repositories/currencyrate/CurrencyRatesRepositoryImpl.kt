package com.example.revoluttestapp.data.repositories.currencyrate

import android.util.Log
import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CurrencyRatesRepositoryImpl(
    private val currencyRateMapper: CurrencyRateMapper,
    private val currencyRatesService: CurrencyRatesService,
    private val rxSchedulers: RxSchedulers
) : CurrencyRatesRepository {
    private val savedCurrencyRates = BehaviorRelay.create<List<CurrencyRate>>()

    override fun getCurrencyRateFromApiFor(currency: Currency): Observable<List<CurrencyRate>> {
        return currencyRatesService.getCurrencyRates(currency.getCode())
            .map { currencyRateMapper.map(it) }
            .doOnNext { savedCurrencyRates.accept(it) }
            .subscribeOn(rxSchedulers.io)
    }

    override fun getCurencyRateFromMemory(): Observable<List<CurrencyRate>> {
        return savedCurrencyRates
    }
}