package com.example.revoluttestapp.currencyconverter.viewmodel

import com.example.revoluttestapp.mvi.CoreState
import com.example.revoluttestapp.currencyconverter.models.UiCurrency

internal data class State(
    val isLoaderShown: Boolean,
    val currencies: List<UiCurrency>
) : CoreState