package com.example.revoluttestapp.presentation.screens.currencies.view

import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyRate
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper

class CurrencyRateUiMapperImpl : CurrencyRateUiMapper {

    override fun map(currencyRates: List<CurrencyRate>): List<UiCurrencyRate> {
        return currencyRates.map {
            UiCurrencyRate(
                it.currency.getCode(),
                it.currency.getFullName(),
                it.rate.toString()
            )
        }
    }
}