package com.example.revoluttestapp.domain

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyConverter {
    private companion object {
        const val DIGITS_AFTER_COMMA = 2
    }

    fun convert(
        baseCurrency: Currency,
        currenciesRates: List<CurrencyRate>
    ): List<CurrencyRate> {
        return currenciesRates.map {
            convertSingleCurrencyRate(baseCurrency, it)
        }
    }

    private fun convertSingleCurrencyRate(
        baseCurrency: Currency,
        currencyRate: CurrencyRate
    ): CurrencyRate {
        val currency = currencyRate.currency
        val convertedAmount = baseCurrency.amount * currencyRate.rate
        val formattedAmount = BigDecimal(convertedAmount)
            .setScale(DIGITS_AFTER_COMMA, RoundingMode.CEILING)
        val convertedCurrency = currency.copy(amount = formattedAmount.toDouble())
        return currencyRate.copy(currency = convertedCurrency)
    }
}