package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace

sealed class Change {
    object ShowLoading: Change()
    data class ShowCurrencies(val uiCurrencies: List<UiCurrencyToConvertPlace>): Change()
    object DoNothing: Change()
}