package com.example.revoluttestapp.domain.models.currency

import java.util.*

data class CurrencyRate(
    val code: String,
    val rate: Double
) {
    private val currency = Currency.getInstance(code)

    val fullName: String
        get() = currency.displayName

    init{

    }
}