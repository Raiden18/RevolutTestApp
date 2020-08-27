package com.example.revoluttestapp.presentation.screens.currencies.view

import com.example.revoluttestapp.domain.models.currency.CurrencyRates
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyRate
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper

class CurrencyRateUiMapperImpl : CurrencyRateUiMapper {

    override fun map(currencyRates: CurrencyRates): List<UiCurrencyRate> {
        return currencyRates.value.map {
            UiCurrencyRate(
                it.code,
                it.fullName,
                it.rate.toString()
            )
        }
    }
}