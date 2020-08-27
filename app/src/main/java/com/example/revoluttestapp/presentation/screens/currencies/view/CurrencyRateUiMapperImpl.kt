package com.example.revoluttestapp.presentation.screens.currencies.view

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper
import java.util.*

class CurrencyRateUiMapperImpl : CurrencyRateUiMapper {

    override fun mapDomainToUi(
        currencyToConvert: Currency,
        currencyRates: List<Currency>
    ): List<UiCurrencyPlace> {
        val currencies = LinkedList<UiCurrencyPlace>()
        val uiCurrencyToConvert = UiCurrencyToConvertPlace(
            currencyToConvert.getCode(),
            currencyToConvert.getFullName(),
            currencyToConvert.getAmount().toString()
        )
        currencies.add(uiCurrencyToConvert)
        currencyRates.forEach {
            val uiConvertedCurrency = UiConvertedCurrency(
                it.getCode(),
                it.getFullName(),
                it.getAmount().toString()
            )
            currencies.add(uiConvertedCurrency)
        }
        return currencies
    }


}