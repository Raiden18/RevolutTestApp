package com.example.revoluttestapp.domain

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyConverter(
    private val codeToCurrencyMapper: CodeToCurrencyMapper
) {
    private companion object {
        const val DIGITS_AFTER_COMMA = 2
    }

    fun convert(
        baseCurrency: Currency,
        currenciesRates: List<CurrencyRate>
    ): List<CurrencyRate> {
        return currenciesRates.map {
            val currency = codeToCurrencyMapper.map(it.currency.getCode())
            val newAmount = baseCurrency.getAmount() * it.rate
            val formattedAmount = BigDecimal(newAmount)
                .setScale(DIGITS_AFTER_COMMA, RoundingMode.CEILING)
            val convertedCurrency = currency.setAmount(formattedAmount.toDouble())
            it.copy(currency = convertedCurrency)
        }
    }
}