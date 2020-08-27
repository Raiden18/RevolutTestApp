package com.example.revoluttestapp.domain.models.currencyrate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CurrencyCodesRateTest{

    @Test
    fun testFullName(){
        val currencyRate = CurrencyRate(CurrencyCodes.UnitedStatesDollar.SHORT_NAME, 1.toDouble())
        assertEquals("US Dollar", currencyRate.fullName)
    }
}