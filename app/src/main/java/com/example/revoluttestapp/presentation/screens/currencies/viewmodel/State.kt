package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.presentation.screens.core.mvi.CoreState
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace

data class State(
    val isLoaderShown: Boolean,
    val currencies: List<UiCurrencyToConvertPlace>
) : CoreState