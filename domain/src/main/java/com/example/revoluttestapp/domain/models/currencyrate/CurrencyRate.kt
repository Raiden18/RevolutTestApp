package com.example.revoluttestapp.domain.models.currencyrate

import com.example.revoluttestapp.domain.models.currencies.Currency
import java.math.BigDecimal
import java.math.RoundingMode

data class CurrencyRate(
    val currency: Currency,
    val rate: Double
) {
    private companion object {
        const val DIGITS_AFTER_COMMA = 2
    }

    val currencyCode
        get() = currency.code

    fun convertCurrencyFrom(baseCurrency: Currency): CurrencyRate {
        val rateBigDecimal = BigDecimal(rate)
        val amountBigDecimal = BigDecimal(baseCurrency.amount)
        val convertedAmount = amountBigDecimal.multiply(rateBigDecimal)
        val formattedAmount = convertedAmount.setScale(DIGITS_AFTER_COMMA, RoundingMode.HALF_UP)
        val convertedCurrency = currency.copy(amount = formattedAmount.toDouble())
        return copy(currency = convertedCurrency)
    }

    fun calculateCurrencyRateFrom(baseCurrency: Currency): CurrencyRate {
        val zeroBigDecimal = BigDecimal(0)
        val oneBigDecimal = BigDecimal(1)
        val rateBigDecimal = BigDecimal(rate)
        val currencyRateForOldSelectedCurrencyValue = if (rate == 0.0) {
            zeroBigDecimal
        } else {
            oneBigDecimal.divide(rateBigDecimal, DIGITS_AFTER_COMMA, RoundingMode.HALF_UP)
        }
        return CurrencyRate(
            baseCurrency.copy(amount = 0.0),
            currencyRateForOldSelectedCurrencyValue.setScale(
                DIGITS_AFTER_COMMA,
                RoundingMode.HALF_UP
            ).toDouble()
        )
    }
}