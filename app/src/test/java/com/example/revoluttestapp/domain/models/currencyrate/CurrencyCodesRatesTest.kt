package com.example.revoluttestapp.domain.models.currencyrate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CurrencyCodesRatesTest {

   @Test
   fun `Should crete empty`(){
       //Given

       //When
       val empty = CurrencyRates.creteEmpty()

       //Then
       assertEquals(CurrencyRates(emptyList()), empty)
   }
}