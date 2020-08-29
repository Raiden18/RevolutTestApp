package com.example.revoluttestapp.data.repositories.currencyrate

import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class CurrencyRatesRepositoryImpl(
    private val currencyRateMapper: CurrencyRateMapper,
    private val currencyRatesService: CurrencyRatesService,
    private val rxSchedulers: RxSchedulers
) : CurrencyRatesRepository {
    private val savedCurrencyRates = BehaviorRelay.create<List<CurrencyRate>>()

    override fun getCurrencyRateFromApiFor(currencyCode: String): Observable<List<CurrencyRate>> {
        return currencyRatesService.getCurrencyRates(currencyCode)
            .map { currencyRateMapper.map(it) }
            .subscribeOn(rxSchedulers.io)
    }

    override fun getCurrencyRateFromMemory(): Observable<List<CurrencyRate>> {
        return savedCurrencyRates
    }

    override fun saveToMemory(currencyRates: List<CurrencyRate>): Completable {
        return Completable.fromAction { savedCurrencyRates.accept(ArrayList(currencyRates)) }
    }
}