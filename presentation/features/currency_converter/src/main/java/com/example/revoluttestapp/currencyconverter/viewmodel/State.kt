package com.example.revoluttestapp.currencyconverter.viewmodel

import com.example.revoluttestapp.core.mvi.CoreState
import com.example.revoluttestapp.currencyconverter.models.UiCurrency

internal data class State(
    val isLoaderShown: Boolean,
    val currencies: List<UiCurrency>,
    val error: Throwable?
) : CoreState{
    companion object{
        fun createEmpty() = State(false, emptyList(), null)
    }
}