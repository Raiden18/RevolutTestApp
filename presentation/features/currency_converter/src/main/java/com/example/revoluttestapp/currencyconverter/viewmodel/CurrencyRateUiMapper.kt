package com.example.revoluttestapp.currencyconverter.viewmodel

import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.models.currencies.Currency

internal interface CurrencyRateUiMapper {
    fun mapToUiCurrency(currency: Currency, flag: Flag): UiCurrency
    fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double
}