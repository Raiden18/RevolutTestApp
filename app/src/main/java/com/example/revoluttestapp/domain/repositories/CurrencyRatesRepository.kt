package com.example.revoluttestapp.domain.repositories

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate

import io.reactivex.rxjava3.core.Observable

interface CurrencyRatesRepository {
    fun getCurrencyRateFromApiFor(currency: Currency): Observable<List<CurrencyRate>>
}