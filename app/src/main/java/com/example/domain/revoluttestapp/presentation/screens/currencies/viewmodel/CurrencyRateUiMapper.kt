package com.example.domain.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.domain.revoluttestapp.presentation.screens.currencies.models.UiCurrency

interface CurrencyRateUiMapper {
    fun mapToUiCurrency(currency: com.example.revoluttestapp.domain.models.currencies.Currency, flag: com.example.revoluttestapp.domain.models.Flag): UiCurrency
    fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double
}