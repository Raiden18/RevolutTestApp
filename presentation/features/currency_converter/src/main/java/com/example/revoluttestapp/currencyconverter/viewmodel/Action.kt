package com.example.revoluttestapp.currencyconverter.viewmodel

import com.example.revoluttestapp.mvi.CoreAction
import com.example.revoluttestapp.currencyconverter.models.UiCurrency

internal sealed class Action: CoreAction {
    object LoadCurrencies: Action()
    data class SelectCurrency(val uiCurrencyPlace: UiCurrency): Action()
    data class AmountOfMoneyChanged(val amount: String): Action()
}