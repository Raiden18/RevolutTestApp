package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class GetCurrencyCodesUseCaseTest {
    private val currencyRatesRepository: CurrencyRatesRepository = mockk(relaxed = true)

    private lateinit var getCurrencyRatesUseCase: GetCurrencyRatesUseCase

    @BeforeEach
    fun setUp() {
        getCurrencyRatesUseCase = GetCurrencyRatesUseCase(currencyRatesRepository)
    }

    @Test
    fun testExecute(){
        getCurrencyRatesUseCase.execute().subscribe()
        verify {
            currencyRatesRepository.getCurrencyFromApi()
        }
    }
}