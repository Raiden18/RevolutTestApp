package com.example.revoluttestapp.presentation.screens.currencies.di

import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.CurrencyConverter
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.usecases.ConvertMoneyUseCase
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import com.example.revoluttestapp.domain.usecases.GetSelectedCurrencyUseCase
import com.example.revoluttestapp.domain.usecases.SaveCurrencyToMemoryUseCase
import com.example.revoluttestapp.presentation.screens.core.di.ApplicationComponent
import com.example.revoluttestapp.presentation.screens.currencies.view.CurrenciesActivity
import com.example.revoluttestapp.presentation.screens.currencies.view.CurrencyRateUiMapperImpl
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CurrenciesScope

@CurrenciesScope
@Component(dependencies = [ApplicationComponent::class], modules = [CurrenciesModule::class])
interface CurrenciesComponent {
    fun inject(currenciesActivity: CurrenciesActivity)
}

@Module
class CurrenciesModule {

    @CurrenciesScope
    @Provides
    fun provideCurrenciesViewModelFactory(getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
                                          currencyRateUiMapper: CurrencyRateUiMapper,
                                          getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
                                          saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
                                          convertMoneyUseCase: ConvertMoneyUseCase,
                                          currencyRatesRepository: CurrencyRatesRepository
    ): CurrenciesViewModelFactory {
        val codeToCurrencyMapper =  CodeToCurrencyMapper()
        val currencyConverter = CurrencyConverter(codeToCurrencyMapper)
        return CurrenciesViewModelFactory(
            getCurrencyRatesUseCase,
            getSelectedCurrencyUseCase,
            saveCurrencyToMemoryUseCase,
            convertMoneyUseCase,
            currencyRateUiMapper,
            codeToCurrencyMapper,
            currencyConverter,
            currencyRatesRepository
        )
    }

    @CurrenciesScope
    @Provides
    fun provideCurrencyRateUiMapper(): CurrencyRateUiMapper {
        return CurrencyRateUiMapperImpl()
    }
}