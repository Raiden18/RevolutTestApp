package com.example.revoluttestapp.data.mappers

import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.data.models.response.CurrencyResponse
import com.example.revoluttestapp.domain.models.currencies.Currency
import java.util.*

class CurrencyRateMapperImpl : CurrencyRateMapper {

    override fun map(currencyResponse: CurrencyResponse): List<CurrencyRate> {
        val currencies = LinkedList<CurrencyRate>()
        for ((key, value) in currencyResponse.rates.entries) {
            val currency = Currency(0.0, key)
            val currencyRate = CurrencyRate(currency, value)
            currencies.add(currencyRate)
        }
        return currencies
    }
}