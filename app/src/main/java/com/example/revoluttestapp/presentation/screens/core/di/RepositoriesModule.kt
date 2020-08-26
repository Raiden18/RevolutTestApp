package com.example.revoluttestapp.presentation.screens.core.di

import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.data.repositories.CurrencyRatesRepositoryImpl
import com.example.revoluttestapp.data.repositories.CurrencyRatesService
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideCurrencyRatesRepository(
        currencyRateMapper: CurrencyRateMapper,
        currencyRatesService: CurrencyRatesService
    ): CurrencyRatesRepository {
        return CurrencyRatesRepositoryImpl(currencyRateMapper, currencyRatesService)
    }
}