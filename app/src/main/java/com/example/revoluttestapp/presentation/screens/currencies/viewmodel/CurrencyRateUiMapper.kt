package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace

interface CurrencyRateUiMapper {
    fun mapDomainToUi(currencyToConvert: Currency, currencyRates: List<CurrencyRate>): List<UiCurrencyPlace>
}