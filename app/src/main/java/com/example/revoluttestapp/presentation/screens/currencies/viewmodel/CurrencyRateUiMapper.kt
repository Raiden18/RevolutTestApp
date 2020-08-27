package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.domain.models.currency.CurrencyRate
import com.example.revoluttestapp.domain.models.currency.CurrencyRates
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyRate

interface CurrencyRateUiMapper {
    fun map(currencyRates: CurrencyRates): List<UiCurrencyRate>
}