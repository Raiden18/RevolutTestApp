package com.example.revoluttestapp.domain.repositories

import com.example.revoluttestapp.domain.models.currencies.Currency
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface CurrencyRepository {
    fun saveToMemoryCurrentCurrency(currency: Currency): Completable
    fun getSelectedCurrencyFromMemory(): Observable<Currency>
}