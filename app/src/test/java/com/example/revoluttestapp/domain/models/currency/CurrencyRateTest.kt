package com.example.revoluttestapp.domain.models.currency

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CurrencyRateTest{

    @Test
    fun testFullName(){
        val currencyRate = CurrencyRate(Currencies.UnitedStatesDollar.SHORT_NAME, 1.toDouble())
        assertEquals("US Dollar", currencyRate.fullName)
    }
}