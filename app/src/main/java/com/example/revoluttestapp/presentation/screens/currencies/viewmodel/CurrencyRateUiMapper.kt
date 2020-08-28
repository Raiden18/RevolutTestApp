package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency

interface CurrencyRateUiMapper {
    fun mapToUiCurrency(currency: Currency, flag: Flag): UiCurrency
    fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double
}