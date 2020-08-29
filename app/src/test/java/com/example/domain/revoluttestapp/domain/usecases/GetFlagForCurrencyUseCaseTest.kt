package com.example.domain.revoluttestapp.domain.usecases

import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class GetFlagForCurrencyUseCaseTest {
    private val flagRepository: com.example.revoluttestapp.domain.repositories.FlagRepository = mockk(relaxed = true)
    private lateinit var getFlagForCurrencyUseCase: com.example.revoluttestapp.domain.usecases.GetFlagForCurrencyUseCase

    @BeforeEach
    fun setUp() {
        clearMocks(flagRepository)
        getFlagForCurrencyUseCase =
            com.example.revoluttestapp.domain.usecases.GetFlagForCurrencyUseCase(
                flagRepository
            )
    }

    @Test
    fun testExecute(){
        val rouble =
            com.example.revoluttestapp.domain.models.currencies.RussianRouble(1123.0)
        getFlagForCurrencyUseCase.execute(rouble).subscribe()
        verify {
            flagRepository.getFlagFor("RU")
        }
    }
}