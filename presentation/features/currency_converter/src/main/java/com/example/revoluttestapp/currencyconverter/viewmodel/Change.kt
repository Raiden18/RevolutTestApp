package com.example.revoluttestapp.currencyconverter.viewmodel

import com.example.revoluttestapp.currencyconverter.models.UiCurrency

internal sealed class Change {
    data class ShowCurrenciesWithoutError(val uiCurrencies: List<UiCurrency>): Change()
    data class ShowPreviousStateErrorAndCurrencies(val uiCurrencies: List<UiCurrency>): Change()
    object DoNothing: Change()
    object ShowLoading: Change()
    object ShowCantSelectNewCurrency: Change()
    object HideCantSelectNewCurrency: Change()
    data class ShowError(val throwable: Throwable): Change()
}