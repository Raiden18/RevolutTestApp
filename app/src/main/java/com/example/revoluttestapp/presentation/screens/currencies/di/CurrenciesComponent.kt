package com.example.revoluttestapp.presentation.screens.currencies.di

import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.presentation.screens.core.di.ApplicationComponent
import com.example.revoluttestapp.presentation.screens.currencies.view.CurrenciesActivity
import com.example.revoluttestapp.presentation.screens.currencies.view.CurrencyRateUiMapperImpl
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper
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
    fun provideCurrenciesViewModelFactory(getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
                                          currencyRateUiMapper: CurrencyRateUiMapper,
                                          getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
                                          saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
                                          subscribeOnCurrenciesRatesUseCase: SubscribeOnCurrenciesRatesUseCase,
                                          rxSchedulers: RxSchedulers
    ): CurrenciesViewModelFactory {
        val codeToCurrencyMapper =
            CodeToCurrencyMapper()
        val currencyConverter =
            CurrencyConverter(
                codeToCurrencyMapper
            )
        val compositeDisposable = CompositeDisposable()
        return CurrenciesViewModelFactory(
            getCurrencyRatesUseCase,
            getSelectedCurrencyUseCase,
            saveCurrencyToMemoryUseCase,
            currencyRateUiMapper,
            codeToCurrencyMapper,
            currencyConverter,
            subscribeOnCurrenciesRatesUseCase,
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