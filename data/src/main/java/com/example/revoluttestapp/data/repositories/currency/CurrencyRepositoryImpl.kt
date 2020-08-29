package com.example.revoluttestapp.data.repositories.currency

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencies.RussianRouble
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class CurrencyRepositoryImpl : CurrencyRepository {
    private companion object {
        val DEFAULT_CURRENCY = RussianRouble(100.0)
    }

    private var savedCurrency: Currency =
        DEFAULT_CURRENCY

    override fun saveToMemoryCurrentCurrency(currency: Currency): Completable {
        return Completable.fromAction {
            savedCurrency = currency
        }
    }

    override fun getCurrentCurrencyFromMemory(): Observable<Currency> {
        return Observable.create {
            it.onNext(savedCurrency)
            it.onComplete()
        }
    }

}