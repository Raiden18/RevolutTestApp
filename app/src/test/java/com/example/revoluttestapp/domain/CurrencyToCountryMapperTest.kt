package com.example.revoluttestapp.domain

import com.example.revoluttestapp.domain.models.countires.Russia
import com.example.revoluttestapp.domain.models.countires.UnitedStates
import com.example.revoluttestapp.domain.models.currencies.RussianRouble
import com.example.revoluttestapp.domain.models.currencies.UnitedStatesDollar
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CurrencyToCountryMapperTest {

    @Test
    fun map() {
        val currencyToCountryMapper = CurrencyToCountryMapper()
        val usDollar = UnitedStatesDollar(123.0)
        assertTrue(currencyToCountryMapper.map(usDollar) is UnitedStates)

        val rouble = RussianRouble(123.0)
        assertTrue(currencyToCountryMapper.map(rouble) is Russia)
    }
}