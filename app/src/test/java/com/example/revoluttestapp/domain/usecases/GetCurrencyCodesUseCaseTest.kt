package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class GetCurrencyCodesUseCaseTest {
    private val currencyRatesRepository: CurrencyRatesRepository = mockk(relaxed = true)
    private val currencyRepository: CurrencyRepository = mockk(relaxed = true)
    private lateinit var getCurrencyRatesUseCase: GetCurrencyRatesUseCase

    @BeforeEach
    fun setUp() {
        getCurrencyRatesUseCase =
            GetCurrencyRatesUseCase(currencyRatesRepository)
    }

    @Test
    fun testExecute() {
        //Given
        val savedCurrency: Currency = mockk(relaxed = true)

        //When
        getCurrencyRatesUseCase.execute().subscribe()

        //Then
        verify {
            currencyRatesRepository.getCurrencyRateFromApiFor(savedCurrency)
        }
    }
}