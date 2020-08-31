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
        val convertedAmount = baseCurrency.amount * rate
        val formattedAmount = BigDecimal(convertedAmount)
            .setScale(DIGITS_AFTER_COMMA, RoundingMode.CEILING)
        val convertedCurrency = currency.copy(amount = formattedAmount.toDouble())
        return copy(currency = convertedCurrency)
    }

    fun calculateCurrencyRateFrom(baseCurrency: Currency): CurrencyRate{
        val currencyRateForOldSelectedCurrencyValue =
            if (rate == 0.0) 0.0 else 1 / rate
        return CurrencyRate(
            baseCurrency.copy(amount = 0.0),
            currencyRateForOldSelectedCurrencyValue.round(2)
        )
    }

    private fun Double.round(places: Int): Double {
        val bigDecimal: BigDecimal = BigDecimal.valueOf(this)
        return bigDecimal.setScale(places, RoundingMode.HALF_UP).toDouble()
    }
}