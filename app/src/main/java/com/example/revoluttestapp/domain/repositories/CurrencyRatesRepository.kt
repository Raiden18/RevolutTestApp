package com.example.revoluttestapp.domain.repositories

import com.example.revoluttestapp.domain.models.currency.CurrencyRates
import io.reactivex.rxjava3.core.Observable

interface CurrencyRatesRepository {
    fun getCurrencyFromApi(): Observable<CurrencyRates>
}