package com.example.revoluttestapp.data.mappers

import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate

interface CurrencyRateMapper {
    fun map(hashMap: HashMap<String, Double>): List<CurrencyRate>
}