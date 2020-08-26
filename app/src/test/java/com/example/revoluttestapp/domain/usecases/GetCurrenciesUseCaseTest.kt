package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GetCurrenciesUseCaseTest {
    private val currencyRatesRepository: CurrencyRatesRepository = mockk(relaxed = true)

    private lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    @BeforeEach
    fun setUp() {
        getCurrenciesUseCase = GetCurrenciesUseCase(currencyRatesRepository)
    }

    @Test
    fun testExecute(){
        getCurrenciesUseCase.execute().subscribe()
        verify {
            currencyRatesRepository.getCurrencyFromApi()
        }
    }
}