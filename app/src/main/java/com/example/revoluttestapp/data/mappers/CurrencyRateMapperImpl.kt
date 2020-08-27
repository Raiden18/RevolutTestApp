package com.example.revoluttestapp.data.mappers

import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.currencies.*
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRates
import java.util.*

class CurrencyRateMapperImpl(
    private val codeToCurrencyMapper: CodeToCurrencyMapper
) : CurrencyRateMapper {

    override fun map(hashMap: HashMap<String, Double>): List<CurrencyRate> {
        val currencies = LinkedList<CurrencyRate>()
        for ((key, value) in hashMap.entries) {
            val currency = codeToCurrencyMapper.map(key)
            val currencyRate = CurrencyRate(currency, value)
            currencies.add(currencyRate)
        }
        return currencies
    }
}