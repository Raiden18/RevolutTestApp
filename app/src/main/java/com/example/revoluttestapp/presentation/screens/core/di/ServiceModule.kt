package com.example.revoluttestapp.presentation.screens.core.di

import com.example.revoluttestapp.data.repositories.currencyrate.CurrencyRatesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideCurrencyRatesService(retrofit: Retrofit): CurrencyRatesService {
        return retrofit.create(CurrencyRatesService::class.java)
    }
}