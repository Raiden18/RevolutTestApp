package com.example.revoluttestapp.presentation.screens.core.di

import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.data.repositories.currency.CurrencyRepositoryImpl
import com.example.revoluttestapp.data.repositories.currencyrate.CurrencyRatesRepositoryImpl
import com.example.revoluttestapp.data.repositories.currencyrate.CurrencyRatesService
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.example.revoluttestapp.domain.utils.RxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideCurrencyRatesRepository(
        currencyRateMapper: CurrencyRateMapper,
        currencyRatesService: CurrencyRatesService,
        rxSchedulers: RxSchedulers
    ): CurrencyRatesRepository {
        return CurrencyRatesRepositoryImpl(
            currencyRateMapper,
            currencyRatesService,
            rxSchedulers
        )
    }

    @Singleton
    @Provides
    fun provideCurrencyRepository(): CurrencyRepository {
        return CurrencyRepositoryImpl()
    }
}