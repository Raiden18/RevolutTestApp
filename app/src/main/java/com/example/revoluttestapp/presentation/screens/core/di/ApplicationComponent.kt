package com.example.revoluttestapp.presentation.screens.core.di

import android.app.Application
import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.data.repositories.currencyrate.CurrencyRatesService
import com.example.revoluttestapp.domain.repositories.CountryRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
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
    fun provideGetCurrenciesUseCase(): GetCurrencyRatesUseCase
    fun provideGetSelectedCurrencyUseCase(): GetSelectedCurrencyUseCase
    fun provideCurrencyRepository(): CurrencyRepository
    fun provideSaveCurrencyToMemoryUseCase(): SaveCurrencyToMemoryUseCase
    fun provideRetrofit(): Retrofit
    fun provideConvertMoneyUseCase(): ConvertMoneyUseCase
    fun provideSubscribeOnCurrenciesRatesUseCase(): SubscribeOnCurrenciesRatesUseCase
    fun provideRxSchedulers(): RxSchedulers
    fun provideCountryRepository(): CountryRepository
}