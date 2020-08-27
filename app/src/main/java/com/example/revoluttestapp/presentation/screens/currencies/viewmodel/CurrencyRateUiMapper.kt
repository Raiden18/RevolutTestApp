package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace

interface CurrencyRateUiMapper {
    fun mapDomainToUi(
        currencyToConvert: Currency,
        currencyRates: List<Currency>,
        cursorPosition: Int
    ): List<UiCurrencyPlace>

    fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double
}