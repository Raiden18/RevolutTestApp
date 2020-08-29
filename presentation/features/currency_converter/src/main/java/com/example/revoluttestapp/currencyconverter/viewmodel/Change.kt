package com.example.revoluttestapp.currencyconverter.viewmodel

import com.example.revoluttestapp.currencyconverter.models.UiCurrency

internal sealed class Change {
    data class ShowCurrencies(val uiCurrencies: List<UiCurrency>): Change()
    object DoNothing: Change()
    object ShowLoading: Change()
    data class ShowError(val throwable: Throwable): Change()
}