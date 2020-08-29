package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Euro
import com.example.revoluttestapp.domain.models.currencies.RussianRouble
import com.example.revoluttestapp.domain.models.currencies.UnitedStatesDollar
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.example.revoluttestapp.domain.utils.Logger
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

internal class UpdateCurrencySelectedCurrencyAndRatesTest {
    private lateinit var updateCurrencySelectedCurrencyAndRates: UpdateCurrencySelectedCurrencyAndRates
    private val currencyRepository: CurrencyRepository = mockk(relaxed = true)
    private val currencyRatesRepository: CurrencyRatesRepository = mockk(relaxed = true)
    private val testSubscriber = TestScheduler()
    private val logger: Logger = mockk(relaxed = true)
    @BeforeEach
    private fun setUp() {
        clearMocks(currencyRepository, currencyRatesRepository)
        updateCurrencySelectedCurrencyAndRates =
            UpdateCurrencySelectedCurrencyAndRates(currencyRepository, currencyRatesRepository, logger)
    }

    @Test
    fun testExecute() {
        //Given
        val selectedCurrency = RussianRouble(123.1)
        val newSelectedCurrency = UnitedStatesDollar(1.5)
        val savedRate1 = CurrencyRate(UnitedStatesDollar(1.1), 1.1)
        val savedRate2 = CurrencyRate(Euro(1.5), 1.5)
        val savedRates = listOf(savedRate1, savedRate2)
        every {
            currencyRepository.getSelectedCurrencyFromMemory()
        } returns Observable.just(selectedCurrency)
        every {
            currencyRatesRepository.getCurrencyRateFromMemory()
        } returns Observable.just(savedRates)

        //When
        updateCurrencySelectedCurrencyAndRates.execute(newSelectedCurrency)
            .subscribeOn(testSubscriber)
            .subscribe()
        testSubscriber.advanceTimeBy(100, TimeUnit.SECONDS)
        //Then
        val russianRate = 1 / 1.1
        val expectedNewCurrencyRate = CurrencyRate(RussianRouble(123.1), russianRate)
        val expectedRatesToSave = listOf(expectedNewCurrencyRate, savedRate2.copy())
        verify{
            currencyRatesRepository.saveToMemory(expectedRatesToSave)
        }

    }
}