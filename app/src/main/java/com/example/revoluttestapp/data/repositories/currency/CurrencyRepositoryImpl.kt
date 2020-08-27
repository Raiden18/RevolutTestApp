package com.example.revoluttestapp.data.repositories.currency

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencies.Euro
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CurrencyRepositoryImpl : CurrencyRepository {
    private companion object {
        val DEFAULT_CURRENCY = Euro(0.0)
    }

    private val savedCurrency = BehaviorSubject.create<Currency>()
        .apply { onNext(DEFAULT_CURRENCY) }

    override fun saveToMemoryCurrentCurrency(currency: Currency): Completable {
        return Completable.fromAction { savedCurrency.onNext(currency) }
    }

    override fun getCurrentCurrencyFromMemory(): Observable<Currency> = savedCurrency

}