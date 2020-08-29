package com.example.revoluttestapp.core.di

import com.example.revoluttestapp.data.repositories.currencyrate.CurrencyRatesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
internal class ServiceModule {

    @Singleton
    @Provides
    fun provideCurrencyRatesService(retrofit: Retrofit): CurrencyRatesService {
        return retrofit.create(CurrencyRatesService::class.java)
    }
}