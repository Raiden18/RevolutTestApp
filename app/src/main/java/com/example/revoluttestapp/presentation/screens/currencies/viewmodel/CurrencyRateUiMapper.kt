package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyRate

interface CurrencyRateUiMapper {
    fun map(currencyRates: List<CurrencyRate>): List<UiCurrencyRate>
}