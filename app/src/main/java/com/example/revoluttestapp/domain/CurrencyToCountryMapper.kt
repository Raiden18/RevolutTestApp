package com.example.revoluttestapp.domain

import com.example.revoluttestapp.domain.models.countires.Country
import com.example.revoluttestapp.domain.models.currencies.Currency

class CurrencyToCountryMapper {
    fun map(currency: Currency): Country {
        val currencyCode = currency.getCode()
        TODO()
    }
}