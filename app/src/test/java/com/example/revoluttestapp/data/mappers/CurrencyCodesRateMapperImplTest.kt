package com.example.revoluttestapp.data.mappers

import com.example.revoluttestapp.data.models.response.CurrencyRatesResponse
import com.example.revoluttestapp.data.models.response.CurrencyResponse
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRates
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class CurrencyCodesRateMapperImplTest {
    private lateinit var currencyRateMapper: CurrencyRateMapperImpl

    @BeforeEach
    fun setUp() {
        currencyRateMapper = CurrencyRateMapperImpl()
    }

    @Test
    fun `Should return empty CurrencyRates if rates of CurrencyResponse is null`() {
        //Given
        val currencyResponse = CurrencyResponse(null, null)

        //When
        val result = currencyRateMapper.map(currencyResponse)

        //Then
        val empty = CurrencyRates.creteEmpty()
        assertEquals(empty, result)
    }

    @Test
    fun `Currencies rates should be empty if it is null`() {
        val currencyResponse = CurrencyResponse(
            null, CurrencyRatesResponse()
        )
        assertEmptyCurrency(currencyResponse)
    }

    @Test
    fun `Should map AustralianDollar if it is not null`() {
        val currencyResponse = createCurrencyRatesResponse(
            CurrencyRatesResponse(
                aUD = 1.toDouble()
            )
        )
        val expectedCurrencyRateMapper = CurrencyRates(
            listOf(
                CurrencyRate(CurrencyCodes.AustralianDollar.SHORT_NAME, 1.toDouble())
            )
        )
        assertNotEmptyCurrency(currencyResponse, expectedCurrencyRateMapper)
    }

    @Test
    fun `Should map BulgarianLev if it is not null`() {
        val currencyResponse = createCurrencyRatesResponse(
            CurrencyRatesResponse(
                bGN = 1.toDouble()
            )
        )
        val expectedCurrencyRateMapper = CurrencyRates(
            listOf(
                CurrencyRate(CurrencyCodes.BulgarianLev.SHORT_NAME, 1.toDouble())
            )
        )
        assertNotEmptyCurrency(currencyResponse, expectedCurrencyRateMapper)
    }


    private fun createCurrencyRatesResponse(currencyRatesResponse: CurrencyRatesResponse): CurrencyResponse {
        return CurrencyResponse(null, currencyRatesResponse)
    }

    private fun assertEmptyCurrency(currencyResponse: CurrencyResponse) {
        val result = currencyRateMapper.map(currencyResponse)
        assertEquals(CurrencyRates.creteEmpty(), result)
    }

    private fun assertNotEmptyCurrency(
        currencyResponse: CurrencyResponse,
        expectedCurrencyRateMapper: CurrencyRates
    ) {
        val result = currencyRateMapper.map(currencyResponse)
        assertEquals(expectedCurrencyRateMapper, result)

    }
}
