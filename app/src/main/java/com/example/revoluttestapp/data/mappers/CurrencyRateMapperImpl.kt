package com.example.revoluttestapp.data.mappers

import com.example.revoluttestapp.data.models.response.CurrencyResponse
import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import java.util.*

class CurrencyRateMapperImpl(
    private val codeToCurrencyMapper: CodeToCurrencyMapper
) : CurrencyRateMapper {

    override fun map(currencyResponse: CurrencyResponse): List<CurrencyRate> {
        val currencies = LinkedList<CurrencyRate>()
        val baseCurrency = codeToCurrencyMapper.map(currencyResponse.baseCurrency)
        for ((key, value) in currencyResponse.rates.entries) {
            val currency = codeToCurrencyMapper.map(key)
            val currencyRate = CurrencyRate(currency, value)
            currencies.add(currencyRate)
        }
        return currencies
    }
}