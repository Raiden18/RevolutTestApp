package com.example.revoluttestapp.presentation.screens.core.di

import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetCurrenciesUseCase(
        currencyRatesRepository: CurrencyRatesRepository,
        currencyRepository: CurrencyRepository
    ): GetCurrencyRatesUseCase {
        return GetCurrencyRatesUseCase(currencyRatesRepository, currencyRepository)
    }
}