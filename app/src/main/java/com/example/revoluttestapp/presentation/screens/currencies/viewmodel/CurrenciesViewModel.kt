package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import com.example.revoluttestapp.domain.usecases.GetSelectedCurrencyUseCase
import com.example.revoluttestapp.domain.usecases.SaveCurrencyToMemoryUseCase
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyRate
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

//TODO: Write tests
//TODO: Add error handling
//TODO: add composite disposables
class CurrenciesViewModel(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper,
    private val codeToCurrencyMapper: CodeToCurrencyMapper
) : ViewModel() {
    private val currencies = BehaviorRelay.create<List<UiCurrencyRate>>()

    init {
        getSelectedCurrencyUseCase.execute()
            .flatMap { getCurrencyRatesUseCase.execute(it) }
            .map { currencyRateUiMapper.map(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currencies.accept(it)
            }, { Timber.e(it) })
    }

    fun selectCurrency(uiCurrencyRate: UiCurrencyRate) {
        //TODO: Add amount converting
        val currency = codeToCurrencyMapper.map(uiCurrencyRate.countryCode)
        saveCurrencyToMemoryUseCase.execute(currency)
            .subscribe({},{Timber.e(it)})
    }

    fun getCurrencies(): Observable<List<UiCurrencyRate>> = currencies
}