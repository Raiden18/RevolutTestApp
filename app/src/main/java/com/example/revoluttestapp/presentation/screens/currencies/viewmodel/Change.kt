package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency

sealed class Change {
    data class ShowCurrencies(val uiCurrencies: List<UiCurrency>): Change()
    object DoNothing: Change()
    object ShowLoading: Change()
}