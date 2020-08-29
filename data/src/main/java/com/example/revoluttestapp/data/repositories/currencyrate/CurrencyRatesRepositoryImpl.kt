package com.example.revoluttestapp.data.repositories.currencyrate

import android.content.Context
import android.system.ErrnoException
import android.util.Log
import com.example.domain.revoluttestapp.R
import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.domain.exceptions.NoInternetConnectionException
import com.example.revoluttestapp.domain.exceptions.UnknownErrorException
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.net.UnknownHostException

class CurrencyRatesRepositoryImpl(
    private val currencyRateMapper: CurrencyRateMapper,
    private val currencyRatesService: CurrencyRatesService,
    private val rxSchedulers: RxSchedulers,
    private val context: Context
) : CurrencyRatesRepository {
    private val savedCurrencyRates = BehaviorRelay.create<List<CurrencyRate>>()

    override fun getCurrencyRateFromApiFor(currencyCode: String): Observable<List<CurrencyRate>> {
        return currencyRatesService.getCurrencyRates(currencyCode)
            .onErrorResumeNext {
                val error = if (it is UnknownHostException) {
                    NoInternetConnectionException(context.getString(R.string.no_internet_connection_message))
                } else {
                    UnknownErrorException(context.getString(R.string.unknown_error_message))
                }
                Observable.error(error)
            }
            .map { currencyRateMapper.map(it) }
            .subscribeOn(rxSchedulers.io)
    }

    override fun getCurrencyRateFromMemory(): Observable<List<CurrencyRate>> {
        return savedCurrencyRates
    }

    override fun saveToMemory(currencyRates: List<CurrencyRate>): Completable {
        return Completable.fromAction { savedCurrencyRates.accept(ArrayList(currencyRates)) }
    }
}