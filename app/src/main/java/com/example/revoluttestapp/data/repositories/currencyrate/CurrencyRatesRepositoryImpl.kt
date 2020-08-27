package com.example.revoluttestapp.data.repositories.currencyrate

import android.util.Log
import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class CurrencyRatesRepositoryImpl(
    private val currencyRateMapper: CurrencyRateMapper,
    private val currencyRatesService: CurrencyRatesService
) : CurrencyRatesRepository {

    override fun getCurrencyRateFromApiFor(currency: Currency): Observable<List<CurrencyRate>> {
        return currencyRatesService.getCurrencyRates(currency.getCode())
            .map { currencyRateMapper.map(it) }
            .subscribeOn(Schedulers.io())
    }
}