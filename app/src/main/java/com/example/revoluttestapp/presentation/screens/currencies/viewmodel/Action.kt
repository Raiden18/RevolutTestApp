package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.presentation.screens.core.mvi.CoreAction
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency

sealed class Action: CoreAction{
    object LoadCurrencies: Action()
    data class SelectCurrency(val uiCurrencyPlace: UiCurrency): Action()
    data class AmountOfMoneyChanged(val amount: String): Action()
}