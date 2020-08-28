package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.RussianRouble
import com.example.revoluttestapp.domain.repositories.FlagRepository
import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class GetFlagForCurrencyUseCaseTest {
    private val flagRepository: FlagRepository = mockk(relaxed = true)
    private lateinit var getFlagForCurrencyUseCase: GetFlagForCurrencyUseCase

    @BeforeEach
    fun setUp() {
        clearMocks(flagRepository)
        getFlagForCurrencyUseCase = GetFlagForCurrencyUseCase(flagRepository)
    }

    @Test
    fun testExecute(){
        val rouble = RussianRouble(1123.0)
        getFlagForCurrencyUseCase.execute(rouble).subscribe()
        verify {
            flagRepository.getFlagFor("RU")
        }
    }
}