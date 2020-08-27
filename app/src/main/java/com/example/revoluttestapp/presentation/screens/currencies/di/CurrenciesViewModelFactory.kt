package com.example.revoluttestapp.presentation.screens.currencies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.CurrencyConverter
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import com.example.revoluttestapp.domain.usecases.GetSelectedCurrencyUseCase
import com.example.revoluttestapp.domain.usecases.SaveCurrencyToMemoryUseCase
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrenciesViewModel
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper

class CurrenciesViewModelFactory(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper,
    private val codeToCurrencyMapper: CodeToCurrencyMapper,
    private val currencyConverter: CurrencyConverter
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrenciesViewModel(
            getCurrencyRatesUseCase,
            getSelectedCurrencyUseCase,
            saveCurrencyToMemoryUseCase,
            currencyRateUiMapper,
            codeToCurrencyMapper,
            currencyConverter
        ) as T
    }
}