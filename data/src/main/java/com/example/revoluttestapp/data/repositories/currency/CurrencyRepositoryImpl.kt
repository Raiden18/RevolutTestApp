package com.example.revoluttestapp.data.repositories.currency

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class CurrencyRepositoryImpl : CurrencyRepository {
    private companion object {
        val DEFAULT_CURRENCY = Currency(100.toDouble(), "EUR")
    }

    private var savedCurrency: Currency = DEFAULT_CURRENCY

    override fun saveToMemoryCurrentCurrency(currency: Currency): Completable {
        return Completable.fromAction {
            savedCurrency = currency
        }
    }

    override fun getSelectedCurrencyFromMemory(): Observable<Currency> {
        return Observable.create {
            it.onNext(savedCurrency)
            it.onComplete()
        }
    }

}