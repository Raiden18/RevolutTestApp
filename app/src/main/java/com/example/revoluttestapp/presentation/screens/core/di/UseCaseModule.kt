package com.example.revoluttestapp.presentation.screens.core.di

import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import com.example.revoluttestapp.domain.repositories.FlagRepository
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetCurrenciesUseCase(
        currencyRatesRepository: CurrencyRatesRepository
    ): GetCurrencyRatesUseCase {
        return GetCurrencyRatesUseCase(currencyRatesRepository)
    }

    @Singleton
    @Provides
    fun provideGetSelectedCurrencyUseCase(currencyRepository: CurrencyRepository): GetSelectedCurrencyUseCase {
        return GetSelectedCurrencyUseCase(currencyRepository)
    }

    @Singleton
    @Provides
    fun provideSaveCurrencyToMemoryUseCase(currencyRepository: CurrencyRepository): SaveCurrencyToMemoryUseCase {
        return SaveCurrencyToMemoryUseCase(currencyRepository)
    }

    @Singleton
    @Provides
    fun provideConvertMoneyUseCase(
        currencyRepository: CurrencyRepository,
        currencyRatesRepository: CurrencyRatesRepository
    ): ConvertMoneyUseCase {
        val codeToCurrencyMapper =
            CodeToCurrencyMapper()
        val currencyConverter =
            CurrencyConverter(
                codeToCurrencyMapper
            )
        return ConvertMoneyUseCase(currencyRepository, currencyConverter, currencyRatesRepository)
    }

    @Singleton
    @Provides
    fun provideSubscribeOnCurrenciesRatesUseCase(
        currencyRepository: CurrencyRepository,
        currencyRatesRepository: CurrencyRatesRepository,
        rxSchedulers: RxSchedulers
    ): UpdateCurrencyRateEverySecondUseCase {
        return UpdateCurrencyRateEverySecondUseCase(
            currencyRepository,
            currencyRatesRepository,
            rxSchedulers
        )
    }

    @Singleton
    @Provides
    fun provideGetFlagForCurrency(flagRepository: FlagRepository): GetFlagForCurrencyUseCase {
        return GetFlagForCurrencyUseCase(flagRepository)
    }

    @Singleton
    @Provides
    fun provideForceUpdateCurrencyRates(currencyRepository: CurrencyRepository,
                                        currencyRatesRepository: CurrencyRatesRepository): ForceUpdateCurrencyRatesUseCase{
        return ForceUpdateCurrencyRatesUseCase(currencyRepository, currencyRatesRepository)
    }
}