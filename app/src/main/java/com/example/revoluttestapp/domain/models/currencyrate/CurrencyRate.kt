package com.example.revoluttestapp.domain.models.currencyrate

import com.example.revoluttestapp.domain.models.currencies.Currency

data class CurrencyRate(
    val currency: Currency,
    val rate: Double
)