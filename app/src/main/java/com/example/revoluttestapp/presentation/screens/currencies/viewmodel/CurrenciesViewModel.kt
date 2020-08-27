package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.CurrencyConverter
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import com.example.revoluttestapp.domain.usecases.GetSelectedCurrencyUseCase
import com.example.revoluttestapp.domain.usecases.SaveCurrencyToMemoryUseCase
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

//TODO: Write tests
//TODO: Add error handling
//TODO: add composite disposables
class CurrenciesViewModel(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper,
    private val codeToCurrencyMapper: CodeToCurrencyMapper,
    private val currencyConverter: CurrencyConverter
) : ViewModel() {
    private val currencies = BehaviorRelay.create<List<UiCurrencyPlace>>()

    init {
        getSelectedCurrencyUseCase.execute()
            .flatMap { selectedCurrency ->
                getCurrencyRatesUseCase.execute(selectedCurrency)
                    .map { currencyConverter.convert(selectedCurrency, it) }
                    .map { currencyRateUiMapper.mapDomainToUi(selectedCurrency, it)}
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currencies.accept(it)
            }, { Timber.e(it) })
    }

    fun selectCurrency(uiCurrency: UiCurrencyPlace) {
        //TODO: Add amount converting
        val currency = codeToCurrencyMapper.map(uiCurrency.countryCode)
        saveCurrencyToMemoryUseCase.execute(currency)
            .subscribe({}, { Timber.e(it) })
    }

    fun getCurrencies(): Observable<List<UiCurrencyPlace>> = currencies
}