package com.example.revoluttestapp.presentation.screens.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.revoluttestapp.domain.usecases.GetCurrenciesUseCase
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrenciesViewModel
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper

class CurrenciesViewModelFactory(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrenciesViewModel(getCurrenciesUseCase, currencyRateUiMapper) as T
    }
}