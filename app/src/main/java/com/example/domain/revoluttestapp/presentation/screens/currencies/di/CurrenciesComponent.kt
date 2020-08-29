package com.example.domain.revoluttestapp.presentation.screens.currencies.di

import com.example.domain.revoluttestapp.presentation.screens.core.di.ApplicationComponent
import com.example.domain.revoluttestapp.presentation.screens.currencies.view.CurrenciesActivity
import com.example.domain.revoluttestapp.presentation.screens.currencies.view.CurrencyRateUiMapperImpl
import com.example.domain.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper
import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
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
    fun provideCurrenciesViewModelFactory(
        getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
        currencyRateUiMapper: CurrencyRateUiMapper,
        getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
        saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
        updateCurrencyRateEverySecondUseCase: UpdateCurrencyRateEverySecondUseCase,
        getFlagForCurrencyUseCase: GetFlagForCurrencyUseCase,
        forceUpdateCurrencyRatesUseCase: ForceUpdateCurrencyRatesUseCase,
        rxSchedulers: RxSchedulers
    ): CurrenciesViewModelFactory {
        val codeToCurrencyMapper =CodeToCurrencyMapper()
        val currencyConverter =CurrencyConverter(codeToCurrencyMapper)
        val compositeDisposable = CompositeDisposable()
        return CurrenciesViewModelFactory(
            getCurrencyRatesUseCase,
            getSelectedCurrencyUseCase,
            saveCurrencyToMemoryUseCase,
            getFlagForCurrencyUseCase,
            forceUpdateCurrencyRatesUseCase,
            currencyRateUiMapper,
            codeToCurrencyMapper,
            currencyConverter,
            updateCurrencyRateEverySecondUseCase,
            compositeDisposable,
            rxSchedulers
        )
    }

    @CurrenciesScope
    @Provides
    fun provideCurrencyRateUiMapper(): CurrencyRateUiMapper {
        return CurrencyRateUiMapperImpl()
    }
}