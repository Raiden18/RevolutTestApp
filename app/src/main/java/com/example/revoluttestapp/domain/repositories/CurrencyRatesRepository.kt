package com.example.revoluttestapp.domain.repositories

import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRates
import io.reactivex.rxjava3.core.Observable

interface CurrencyRatesRepository {
    fun getCurrencyFromApi(): Observable<List<CurrencyRate>>
}