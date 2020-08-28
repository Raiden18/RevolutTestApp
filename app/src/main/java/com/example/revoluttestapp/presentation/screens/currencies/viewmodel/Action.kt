package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.presentation.screens.core.mvi.CoreAction
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace

sealed class Action: CoreAction{
    object LoadCurrencies: Action()
    data class SelectCurrency(val uiCurrencyPlace: UiCurrencyPlace): Action()
}