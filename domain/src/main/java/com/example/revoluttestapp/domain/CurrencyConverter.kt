package com.example.revoluttestapp.domain

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyConverter {

    fun convert(
        baseCurrency: Currency,
        currenciesRates: List<CurrencyRate>
    ): List<CurrencyRate> {
        return currenciesRates.map {
            it.convertCurrencyFrom(baseCurrency)
        }
    }
}