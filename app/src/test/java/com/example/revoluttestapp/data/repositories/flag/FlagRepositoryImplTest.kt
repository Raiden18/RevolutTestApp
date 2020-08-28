package com.example.revoluttestapp.data.repositories.flag

import com.example.revoluttestapp.domain.models.Flag
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class FlagRepositoryImplTest {
    lateinit var flagsRepositoryImpl: FlagRepositoryImpl
    @BeforeEach
    fun setUp() {
        flagsRepositoryImpl = FlagRepositoryImpl()
    }

    @Test
    fun getFlagFor() {
        flagsRepositoryImpl.getFlagFor("ru")
            .test()
            .assertResult(Flag("https://www.countryflags.io/ru/shiny/64.png"))
    }
}