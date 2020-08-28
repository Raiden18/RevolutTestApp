package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.presentation.screens.core.mvi.CoreState
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency

data class State(
    val isLoaderShown: Boolean,
    val currencies: List<UiCurrency>
) : CoreState