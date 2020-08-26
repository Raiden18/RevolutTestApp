package com.example.revoluttestapp.domain.models.currency

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CurrencyRateTest {

    @Test
    fun `Should create empty CurrencyRate`() {
        //Given

        //When
        val empty = CurrencyRate.createEmpty()

        //Then
        assertEquals(
            CurrencyRate("", 0.toDouble()),
            empty
        )
    }

    @Test
    fun `Should return true if currency rate is empty`(){
        //Given
        val empty = CurrencyRate.createEmpty()

        //When and Then
        assertTrue(empty.isEmpty())
    }

    @Test
    fun `Should return false if currency rate is not empty`(){
        //Given
        val empty = CurrencyRate("123", 1.toDouble())

        //When and Then
        assertFalse(empty.isEmpty())
    }

}