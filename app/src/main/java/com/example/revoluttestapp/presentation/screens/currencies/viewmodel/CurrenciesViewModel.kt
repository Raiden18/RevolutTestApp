package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

//TODO: Write tests
//TODO: Add error handling
//TODO: RETRY AFTER LOOSING INTERNET CONNECTION
class CurrenciesViewModel(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val getFlagForCurrencyUseCase: GetFlagForCurrencyUseCase,
    private val forceUpdateCurrencyRatesUseCase: ForceUpdateCurrencyRatesUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper,
    private val codeToCurrencyMapper: CodeToCurrencyMapper,
    private val currencyConverter: CurrencyConverter,
    private val updateCurrencyRateEverySecondUseCase: UpdateCurrencyRateEverySecondUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val rxSchedulers: RxSchedulers
) : ViewModel() {
    private val currencies =MutableLiveData<List<UiCurrencyToConvertPlace>>()
    private val isShowLoader = BehaviorRelay.create<Boolean>()
    private val currentAmountOfMoney = BehaviorRelay.create<String>()

    init {
        isShowLoader.accept(true)
        subscribeOnRates()
        subscribeOnCurrencies()

    }

    fun selectCurrency(uiCurrency: UiCurrencyPlace) {
        getSelectedCurrencyUseCase.execute()
            .map {
                val currency = codeToCurrencyMapper.map(uiCurrency.currencyCode)
                val amountOfMoney = currencyRateUiMapper.mapAmountOfMoneyToDouble(
                    uiCurrency.amountOfMoney
                )
                currency.setAmount(amountOfMoney)
            }.concatMapCompletable { saveCurrencyToMemoryUseCase.execute(it) }
            .subscribeOn(rxSchedulers.io)
            .subscribe()
            .also { compositeDisposable.add(it) }
    }

    fun onAmountOfMoneyChanged(amountOfMoney: String) {
        currentAmountOfMoney.accept(amountOfMoney)
    }

    fun getCurrencies(): LiveData<List<UiCurrencyToConvertPlace>> = currencies




    fun isShowLoader(): Observable<Boolean> = isShowLoader

    private fun subscribeOnCurrencies() {
        getCurrencyRatesUseCase.execute()
            .flatMap { rates ->
                getSelectedCurrencyUseCase.execute()
                    .distinctUntilChanged()
                    .map { currencyConverter.convert(it, rates) }
                    .flatMap { convertedCurrencies ->
                        loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies)
                    }.flatMap { uiConvertedCurrencies ->
                        loadFlagForSelectedCurrencyAndMapToUi()
                            .map { setCurrencyToConvertToTopOfList(it, uiConvertedCurrencies) }
                    }
            }
            .observeOn(rxSchedulers.main)
            .doOnNext { isShowLoader.accept(false) }
            .subscribe({
                currencies.value = it
            }, { Timber.e(it) })
            .also { compositeDisposable.add(it) }
    }

    private fun loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies: List<Currency>): Observable<ArrayList<UiCurrencyToConvertPlace>> {
        return Observable.fromIterable(convertedCurrencies)
            .flatMap { currency ->
                getFlagForCurrencyUseCase.execute(currency)
                    .map { flag ->
                        currencyRateUiMapper.mapCurrencyToConvert(
                            currency,
                            flag
                        )
                    }
            }.collect({ ArrayList<UiCurrencyToConvertPlace>() },
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
        updateCurrencyRateEverySecondUseCase.execute()
            .subscribe()
            .also { compositeDisposable.add(it) }
    }

    private fun setCurrencyToConvertToTopOfList(
        currencyToConvertPlace: UiCurrencyToConvertPlace,
        uiConvertedCurrencies: List<UiCurrencyToConvertPlace>
    ): List<UiCurrencyToConvertPlace> {
        val linkedList = LinkedList<UiCurrencyToConvertPlace>()
        linkedList.add(currencyToConvertPlace)
        linkedList.addAll(uiConvertedCurrencies)
        return linkedList
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}