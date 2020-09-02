package com.example.revoluttestapp.domain.models.currencyrate

import com.example.revoluttestapp.domain.models.currencies.Currency
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CurrencyRateTest{

    @Test
    fun testConvertCurrencyFrom(){
        //Given
        val currencyOne = Currency(10.toDouble(), "RUB")
        val currencyRateOne = CurrencyRate(currencyOne, 0.21)
        val baseCurrency = Currency(100.toDouble(), "USD")

        //When
        val convertedCurrency = currencyRateOne.convertCurrencyFrom(baseCurrency)

        //Then
        val expectedConvertedCurrency = currencyOne.copy(amount = 21.toDouble())
        val expectedCurrencyRate = currencyRateOne.copy(currency = expectedConvertedCurrency)
        assertEquals(expectedCurrencyRate, convertedCurrency)
    }

    @Test
    fun `Should calculate currency rate if rate is zero`(){
        //Given
        val currencyOne = Currency(10.toDouble(), "RUB")
        val currencyRateOne = CurrencyRate(currencyOne, 0.21)
        val baseCurrency = Currency(100.toDouble(), "USD")

        //When
        val convertedCurrency = currencyRateOne.calculateCurrencyRateFrom(baseCurrency)

        //Then
        val expectedReversedCurrency = baseCurrency.copy(0.0)
        val expectedCurrencyRate = CurrencyRate(
            expectedReversedCurrency,
            4.76
        )
    }
}