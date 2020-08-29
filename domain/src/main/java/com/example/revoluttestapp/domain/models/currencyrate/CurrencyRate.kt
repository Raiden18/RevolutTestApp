package com.example.revoluttestapp.domain.models.currencyrate

import com.example.revoluttestapp.domain.models.currencies.Currency

//TODO: Implement following of Demetry Low
data class CurrencyRate(
    val currency: Currency,
    val rate: Double
)