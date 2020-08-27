package com.example.revoluttestapp.data.mappers

import com.example.revoluttestapp.data.models.response.CurrencyResponse
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate

interface CurrencyRateMapper {
    fun map(currencyResponse: CurrencyResponse): List<CurrencyRate>
}