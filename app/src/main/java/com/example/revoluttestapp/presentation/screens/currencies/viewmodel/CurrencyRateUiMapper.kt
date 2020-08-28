package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace

interface CurrencyRateUiMapper {
    fun mapConvertedCurrencies(currency: Currency, flag: Flag): UiConvertedCurrency
    fun mapCurrencyToConvert(currencyToConvert: Currency, flag: Flag): UiCurrencyToConvertPlace
    fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double
}