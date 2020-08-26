package com.example.revoluttestapp.presentation.screens.core.di

import android.app.Application
import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.data.repositories.CurrencyRatesService
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.usecases.GetCurrenciesUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GlobalModule::class, RepositoriesModule::class, UseCaseModule::class, ServiceModule::class])
interface ApplicationComponent {
    fun provideApplication(): Application

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun provideApplication(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun provideCurrencyRatesRepository(): CurrencyRatesRepository
    fun provideCurrencyRateMapper(): CurrencyRateMapper
    fun provideCurrencyRatesService(): CurrencyRatesService
    fun provideGetCurrenciesUseCase(): GetCurrenciesUseCase
}