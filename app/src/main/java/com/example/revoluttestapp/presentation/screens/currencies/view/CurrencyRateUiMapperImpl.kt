package com.example.revoluttestapp.presentation.screens.currencies.view

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper
import java.util.*

class CurrencyRateUiMapperImpl : CurrencyRateUiMapper {

    override fun mapDomainToUi(
        currencyToConvert: Currency,
        currencyRates: List<CurrencyRate>
    ): List<UiCurrencyPlace> {
        val currencies = LinkedList<UiCurrencyPlace>()
        val uiCurrencyToConvert = mapCurrencyToUi(currencyToConvert)
        val uiCurrencyToConvertPlace = UiCurrencyToConvertPlace(uiCurrencyToConvert)
        currencies.add(uiCurrencyToConvertPlace)
        currencyRates.forEach {
            val uiCurrency = mapCurrencyToUi(it.currency)
            val uiConvertedCurrency = UiConvertedCurrency(uiCurrency)
            currencies.add(uiConvertedCurrency)
        }
        return currencies
    }

    private fun mapCurrencyToUi(currencyToConvert: Currency): UiCurrency {
        return UiCurrency(
            currencyToConvert.getCode(),
            currencyToConvert.getFullName(),
            currencyToConvert.toString()
        )
    }
}