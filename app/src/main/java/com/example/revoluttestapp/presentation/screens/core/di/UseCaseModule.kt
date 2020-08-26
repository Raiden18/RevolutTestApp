package com.example.revoluttestapp.presentation.screens.core.di

import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.usecases.GetCurrenciesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetCurrenciesUseCase(
        currencyRatesRepository: CurrencyRatesRepository
    ): GetCurrenciesUseCase {
        return GetCurrenciesUseCase(currencyRatesRepository)
    }
}