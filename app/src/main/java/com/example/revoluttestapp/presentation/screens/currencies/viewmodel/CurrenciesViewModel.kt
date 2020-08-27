package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import com.example.revoluttestapp.domain.usecases.GetSelectedCurrencyUseCase
import com.example.revoluttestapp.domain.usecases.SaveCurrencyToMemoryUseCase
import com.example.revoluttestapp.domain.usecases.SubscribeOnCurrenciesRatesUseCase
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

//TODO: Write tests
//TODO: Add error handling
//TODO: Make remember currrent cusror position
class CurrenciesViewModel(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper,
    private val codeToCurrencyMapper: CodeToCurrencyMapper,
    private val currencyConverter: CurrencyConverter,
    private val subscribeOnCurrenciesRatesUseCase: SubscribeOnCurrenciesRatesUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val rxSchedulers: RxSchedulers
) : ViewModel() {
    private val currencies = BehaviorRelay.create<List<UiCurrencyPlace>>()
    private val isShowLoader = BehaviorRelay.create<Boolean>()
    private lateinit var currencyToChange: Currency
    private var cursorPosition = 0

    init {
        isShowLoader.accept(true)
        subscribeOnRates()
        subscribeOnCurrencies()
    }

    fun selectCurrency(uiCurrency: UiCurrencyPlace) {
        val currency = codeToCurrencyMapper.map(uiCurrency.countryCode)
        val amountOfMoney = currencyRateUiMapper.mapAmountOfMoneyToDouble(uiCurrency.amountOfMoney)
        val currencyWithAmount = currency.setAmount(amountOfMoney)
        saveCurrencyToMemoryUseCase.execute(currencyWithAmount)
            .subscribe({}, {})
            .also { compositeDisposable.add(it) }
    }

    fun onAmountOfMoneyChanged(amountOfMoney: String, cursorPosition: Int) {
        this.cursorPosition = cursorPosition
        val amountOfMoneyDouble = currencyRateUiMapper.mapAmountOfMoneyToDouble(amountOfMoney)
        val newCurrencyToChange = currencyToChange.setAmount(amountOfMoneyDouble)
        saveCurrencyToMemoryUseCase.execute(newCurrencyToChange)
            .subscribe()
            .also { compositeDisposable.add(it) }
    }

    fun getCurrencies(): Observable<List<UiCurrencyPlace>> = currencies
    fun isShowLoader(): Observable<Boolean> = isShowLoader

    private fun subscribeOnCurrencies() {
        getCurrencyRatesUseCase.execute()
            .flatMap { rates ->
                getSelectedCurrencyUseCase.execute()
                    .doOnNext { currencyToChange = it }
                    .map { selectedCurrency ->
                        val convertedCurrencies = currencyConverter.convert(selectedCurrency, rates)
                        currencyRateUiMapper.mapDomainToUi(selectedCurrency, convertedCurrencies, cursorPosition)
                    }
            }
            .observeOn(rxSchedulers.main)
            .doOnNext { isShowLoader.accept(false) }
            .subscribe({
                currencies.accept(it)
            }, { })
            .also { compositeDisposable.add(it) }
    }

    private fun subscribeOnRates() {
        subscribeOnCurrenciesRatesUseCase.execute()
            .subscribe()
            .also { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}