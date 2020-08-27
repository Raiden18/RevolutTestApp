package com.example.revoluttestapp.domain.models

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import java.math.BigDecimal

class CurrencyConverter(
    private val codeToCurrencyMapper: CodeToCurrencyMapper
) {
    private companion object {
        const val DIGITS_AFTER_COMMA = 2
    }

    fun convert(
        baseCurrency: Currency,
        currenciesRates: List<CurrencyRate>
    ): List<Currency> {
        return currenciesRates.map {
            val currency = codeToCurrencyMapper.map(it.currency.getCode())
            val newAmount = baseCurrency.getAmount() * it.rate
            val formattedAmount =
                BigDecimal(newAmount).setScale(DIGITS_AFTER_COMMA, BigDecimal.ROUND_HALF_UP)
            currency.setAmount(formattedAmount.toDouble())
        }
    }
}