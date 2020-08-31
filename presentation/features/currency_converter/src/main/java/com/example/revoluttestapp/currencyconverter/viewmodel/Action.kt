package com.example.revoluttestapp.currencyconverter.viewmodel

import com.example.revoluttestapp.core.mvi.CoreAction
import com.example.revoluttestapp.currencyconverter.models.UiCurrency

internal sealed class Action: CoreAction {
    object LoadCurrencies: Action()
    object SubscribeOnCurrencyRates: Action()
    object CancelUpdatingRates: Action()
    data class SelectCurrency(val uiCurrencyPlace: UiCurrency): Action()
    data class AmountOfMoneyChanged(val amount: String): Action()
    object HideCantSelectCurrency: Action()
}