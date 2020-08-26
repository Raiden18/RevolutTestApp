package com.example.revoluttestapp.data.repositories

import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.data.models.response.CurrencyResponse
import com.example.revoluttestapp.domain.models.currency.CurrencyRates
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class CurrencyRatesRepositoryImplTest {
    private val currencyRateMapper: CurrencyRateMapper = mockk(relaxed = true)
    private val currencyRatesService: CurrencyRatesService = mockk(relaxed = true)
    private lateinit var currencyRatesRepository: CurrencyRatesRepositoryImpl

    @BeforeEach
    fun setUp() {
        clearMocks(currencyRateMapper, currencyRatesService)
        currencyRatesRepository = CurrencyRatesRepositoryImpl(
            currencyRateMapper,
            currencyRatesService
        )
    }

    @Test
    fun `Should get currency rates, and return`() {
        //Given
        val currencyRatesResponse: CurrencyResponse = mockk(relaxed = true)
        val currencyRates: CurrencyRates = mockk(relaxed = true)
        every {
            currencyRatesService.getCurrencyRates()
        } returns Observable.just(currencyRatesResponse)
        every {
            currencyRateMapper.map(any())
        } returns currencyRates

        //When and Then
        currencyRatesRepository.getCurrencyFromApi()
            .test()
            .assertValue(currencyRates)
    }
}