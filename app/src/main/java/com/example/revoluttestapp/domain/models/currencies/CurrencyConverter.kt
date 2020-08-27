package com.example.revoluttestapp.domain.models.currencies

import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import java.math.BigDecimal

class CurrencyConverter(
    private val baseCurrency: Currency,
    private val currenciesRates: List<CurrencyRate>,
    private val codeToCurrencyMapper: CodeToCurrencyMapper
) {

    fun convert(): List<Currency> {
        return currenciesRates.map {
            val currency = codeToCurrencyMapper.map(it.currency.getCode())
            val newAmount = baseCurrency.getAmount() * it.rate
            val formattedAmount = BigDecimal(newAmount).setScale(2, BigDecimal.ROUND_HALF_UP)
            currency.setAmount(formattedAmount.toDouble())
        }
    }
}