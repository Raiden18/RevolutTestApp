package com.example.revoluttestapp.currencyconverter.di

import android.app.Application
import com.example.revoluttestapp.core.di.ApplicationComponent
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.currencyconverter.CurrencyRateUiMapperImpl
import com.example.revoluttestapp.currencyconverter.viewmodel.CurrencyRateUiMapper
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.Logger
import com.example.revoluttestapp.domain.utils.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CurrenciesScope

@CurrenciesScope
@Component(dependencies = [ApplicationComponent::class], modules = [CurrenciesModule::class])
internal interface CurrenciesComponent {
    fun inject(currenciesActivity: CurrenciesActivity)
}

@Module
internal class CurrenciesModule {

    @CurrenciesScope
    @Provides
    fun provideCurrenciesViewModelFactory(
        getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
        currencyRateUiMapper: CurrencyRateUiMapper,
        getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
        saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
        updateCurrencyRateEverySecondUseCase: UpdateCurrencyRateEverySecondUseCase,
        getFlagForCurrencyUseCase: GetFlagForCurrencyUseCase,
        convertMoneyUseCase: ConvertMoneyUseCase,
        updateCurrencySelectedCurrencyAndRates: UpdateCurrencySelectedCurrencyAndRates,
        rxSchedulers: RxSchedulers,
        logger: Logger
    ): CurrenciesViewModelFactory {
        val compositeDisposable = CompositeDisposable()
        return CurrenciesViewModelFactory(
            getCurrencyRatesUseCase,
            getSelectedCurrencyUseCase,
            saveCurrencyToMemoryUseCase,
            getFlagForCurrencyUseCase,
            convertMoneyUseCase,
            updateCurrencySelectedCurrencyAndRates,
            currencyRateUiMapper,
            updateCurrencyRateEverySecondUseCase,
            compositeDisposable,
            rxSchedulers,
            logger
        )
    }

    @CurrenciesScope
    @Provides
    fun provideCurrencyRateUiMapper(application: Application): CurrencyRateUiMapper {
        return CurrencyRateUiMapperImpl(application)
    }
}