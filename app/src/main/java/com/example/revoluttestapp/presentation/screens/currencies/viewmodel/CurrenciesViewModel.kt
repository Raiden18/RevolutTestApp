package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

//TODO: Write tests
//TODO: Add error handling
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
        Log.i("HUI", "selectCurrency")
        Observable.create<Currency> {
            val currency = codeToCurrencyMapper.map(uiCurrency.currencyCode)
            val amountOfMoney = currencyRateUiMapper.mapAmountOfMoneyToDouble(
                uiCurrency.amountOfMoney
            )
            val currencyWithAmount = currency.setAmount(amountOfMoney)
            it.onNext(currencyWithAmount)
            it.onComplete()
        }.flatMapCompletable { saveCurrencyToMemoryUseCase.execute(it) }
            .subscribeOn(rxSchedulers.io)
            .subscribe()
            .also { compositeDisposable.add(it) }
    }

    fun onAmountOfMoneyChanged(amountOfMoney: String) {
        Log.i("HUI", "onAmountOfMoneyChanged")
        Observable.create<Currency> {
            val amountOfMoneyDouble = currencyRateUiMapper.mapAmountOfMoneyToDouble(amountOfMoney)
            val newCurrencyToChange = currencyToChange.setAmount(amountOfMoneyDouble)
            it.onNext(newCurrencyToChange)
            it.onComplete()
        }.flatMapCompletable { saveCurrencyToMemoryUseCase.execute(it) }
            .subscribeOn(rxSchedulers.io)
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
                        loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies)
                    }
                    .flatMap { uiConvertedCurrencies ->
                        loadFlagForSelectedCurrencyAndMapToUi()
                            .map { setCurrencyToConvertToTopOfList(it, uiConvertedCurrencies) }
                    }
            }
            .observeOn(rxSchedulers.main)
            .doOnNext { isShowLoader.accept(false) }
            .subscribe({
                currencies.accept(it)
            }, { Timber.e(it) })
            .also { compositeDisposable.add(it) }
    }

    private fun loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies: List<Currency>): Observable<ArrayList<UiConvertedCurrency>> {
        return Observable.fromIterable(convertedCurrencies)
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

    private fun loadFlagForSelectedCurrencyAndMapToUi(): Observable<UiCurrencyToConvertPlace> {
        return getSelectedCurrencyUseCase.execute()
            .flatMap { selectedCurrency ->
                getFlagForCurrencyUseCase.execute(selectedCurrency)
                    .map { currencyRateUiMapper.mapCurrencyToConvert(selectedCurrency, it) }
            }
    }

    private fun subscribeOnRates() {
        subscribeOnCurrenciesRatesUseCase.execute()
            .subscribe()
            .also { compositeDisposable.add(it) }
    }

    private fun setCurrencyToConvertToTopOfList(
        currencyToConvertPlace: UiCurrencyToConvertPlace,
        uiConvertedCurrencies: List<UiConvertedCurrency>
    ): List<UiCurrencyPlace> {
        val linkedList = LinkedList<UiCurrencyPlace>()
        linkedList.add(currencyToConvertPlace)
        linkedList.addAll(uiConvertedCurrencies)
        return linkedList
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}