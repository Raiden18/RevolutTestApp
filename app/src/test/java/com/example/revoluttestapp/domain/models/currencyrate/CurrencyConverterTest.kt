package com.example.revoluttestapp.domain.models.currencyrate

import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.currencies.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Euro
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CurrencyConverterTest {
    private val codeToCurrencyMapper: CodeToCurrencyMapper = mockk(relaxed = true)

    @BeforeEach
    fun setUp(){
        clearMocks(codeToCurrencyMapper)
    }

    @Test
    fun convert() {
        val euro = Euro(100.toDouble())
        val currenciesRates = CurrencyRate("USD", 1.1)
        val usDollar = USDollar(0.0)
        every {
            codeToCurrencyMapper.map(any())
        } returns usDollar
        val currencyConverter =
            CurrencyConverter(
                euro,
                listOf(currenciesRates),
                codeToCurrencyMapper
            )


        assertEquals(listOf(USDollar(110.0)), currencyConverter.convert())
    }
}