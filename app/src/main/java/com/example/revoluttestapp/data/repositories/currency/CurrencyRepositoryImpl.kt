package com.example.revoluttestapp.data.repositories.currency

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencies.Euro
import com.example.revoluttestapp.domain.models.currencies.RussianRouble
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class CurrencyRepositoryImpl : CurrencyRepository {
    private companion object {
        val DEFAULT_CURRENCY = RussianRouble(100.0)
    }

    private val savedCurrency = BehaviorRelay.create<Currency>()
        .apply { accept(DEFAULT_CURRENCY) }

    override fun saveToMemoryCurrentCurrency(currency: Currency): Completable {
        return Completable.fromAction { savedCurrency.accept(currency) }
    }

    override fun getCurrentCurrencyFromMemory(): Observable<Currency> = savedCurrency

}