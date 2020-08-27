package com.example.revoluttestapp.presentation.screens.currencies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrenciesViewModel
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper

class CurrenciesViewModelFactory(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrenciesViewModel(getCurrencyRatesUseCase, currencyRateUiMapper) as T
    }
}