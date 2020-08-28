package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

//TODO: Write tests
//TODO: Add error handling
//TODO: Make remember currrent cusror position
class CurrenciesViewModel(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val getFlagForCurrencyUseCase: GetFlagForCurrencyUseCase,
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

    init {
        isShowLoader.accept(true)
        subscribeOnRates()
        subscribeOnCurrencies()
    }

    fun selectCurrency(uiCurrency: UiCurrencyPlace) {
        val currency = codeToCurrencyMapper.map(uiCurrency.currencyCode)
        val amountOfMoney = currencyRateUiMapper.mapAmountOfMoneyToDouble(uiCurrency.amountOfMoney)
        val currencyWithAmount = currency.setAmount(amountOfMoney)
        saveCurrencyToMemoryUseCase.execute(currencyWithAmount)
            .subscribe({}, {})
            .also { compositeDisposable.add(it) }
    }

    fun onAmountOfMoneyChanged(amountOfMoney: String) {
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
                    .map { currencyConverter.convert(it, rates) }
                    .flatMap { convertedCurrencies ->
                        loadFlagsForConvertedCurrencies(convertedCurrencies)
                    }
                    .flatMap { uiConvertedCurrencies ->
                        getSelectedCurrencyUseCase.execute()
                            .flatMap {selectedCurrency->
                                getFlagForCurrencyUseCase.execute(selectedCurrency)
                                    .map { currencyRateUiMapper.mapCurrencyToConvert(selectedCurrency, it) }
                            }.map {
                                val linkedList = LinkedList<UiCurrencyPlace>()
                                linkedList.add(it)
                                linkedList.addAll(uiConvertedCurrencies)
                                return@map linkedList
                            }
                    }


            }
            .observeOn(rxSchedulers.main)
            .doOnNext { isShowLoader.accept(false) }
            .subscribe({
                currencies.accept(it)
            }, { Timber.e(it) })
            .also { compositeDisposable.add(it) }
    }

    private fun loadFlagsForConvertedCurrencies(list: List<Currency>): Observable<ArrayList<UiConvertedCurrency>> {
        return Observable.fromIterable(list)
            .flatMap { currency ->
                getFlagForCurrencyUseCase.execute(currency)
                    .map { flag ->
                        currencyRateUiMapper.mapConvertedCurrencies(
                            currency,
                            flag
                        )
                    }
            }.collect({ ArrayList<UiConvertedCurrency>() },
                { collection, item -> collection.add(item) })
            .toObservable()
    }

    private fun addSelectedCurrencyToTop() {

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