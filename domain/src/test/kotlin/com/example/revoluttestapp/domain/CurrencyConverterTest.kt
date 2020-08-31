package com.example.revoluttestapp.domain

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CurrencyConverterTest{
    lateinit var currencyConverter: CurrencyConverter

    @BeforeEach
    fun setUp(){
        currencyConverter = CurrencyConverter()
    }

    @Test
    fun testConvert(){
        //Given
        val baseCurrency = Currency(100.toDouble(), "EUR")
        val dollar = createDollar(100.toDouble())
        val rub = createRub(100.toDouble())
        val currencyRates = listOf(CurrencyRate(dollar, 10.toDouble()), CurrencyRate(rub, 100.toDouble()))

        //When
        val convertedValue = currencyConverter.convert(baseCurrency, currencyRates)

        //Then
        val expectedValue = listOf(
            CurrencyRate(createDollar(1000.toDouble()), 10.toDouble()),
            CurrencyRate(createRub(10000.toDouble()), 100.toDouble())
        )
        assertEquals(expectedValue, convertedValue)
    }

    private fun createDollar(amount: Double): Currency{
        return Currency(amount, "USD")
    }

    private fun createRub(amount: Double): Currency{
        return Currency(amount, "RUB")
    }
}