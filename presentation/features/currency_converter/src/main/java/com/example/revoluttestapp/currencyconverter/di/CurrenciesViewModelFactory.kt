package com.example.revoluttestapp.currencyconverter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.revoluttestapp.currencyconverter.viewmodel.CurrenciesViewModel
import com.example.revoluttestapp.currencyconverter.viewmodel.CurrencyRateUiMapper
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.Logger
import com.example.revoluttestapp.domain.utils.RxSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

internal class CurrenciesViewModelFactory(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val getFlagForCurrencyUseCase: GetFlagForCurrencyUseCase,
    private val convertMoneyUseCase: ConvertMoneyUseCase,
    private val updateCurrencySelectedCurrencyAndRates: UpdateCurrencySelectedCurrencyAndRates,
    private val currencyRateUiMapper: CurrencyRateUiMapper,
    private val updateCurrencyRateEverySecondUseCase: UpdateCurrencyRateEverySecondUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val rxSchedulers: RxSchedulers,
    private val logger: Logger
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrenciesViewModel(
            getCurrencyRatesUseCase,
            getSelectedCurrencyUseCase,
            saveCurrencyToMemoryUseCase,
            getFlagForCurrencyUseCase,
            updateCurrencySelectedCurrencyAndRates,
            convertMoneyUseCase,
            currencyRateUiMapper,
            updateCurrencyRateEverySecondUseCase,
            compositeDisposable,
            rxSchedulers,
            logger
        ) as T
    }
}