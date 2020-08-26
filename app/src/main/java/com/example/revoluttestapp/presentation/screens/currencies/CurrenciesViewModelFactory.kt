package com.example.revoluttestapp.presentation.screens.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.revoluttestapp.domain.usecases.GetCurrenciesUseCase
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrenciesViewModel

class CurrenciesViewModelFactory(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrenciesViewModel(getCurrenciesUseCase) as T
    }
}