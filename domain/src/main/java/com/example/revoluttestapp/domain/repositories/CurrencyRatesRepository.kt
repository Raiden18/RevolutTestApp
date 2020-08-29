package com.example.revoluttestapp.domain.repositories

import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface CurrencyRatesRepository {
    fun getCurrencyRateFromApiFor(currencyCode: String): Observable<List<CurrencyRate>>
    fun getCurrencyRateFromMemory(): Observable<List<CurrencyRate>>
    fun saveToMemory(currencyRates: List<CurrencyRate>): Completable
}