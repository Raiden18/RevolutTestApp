package com.example.revoluttestapp.currencyconverter.viewmodel

import android.util.Log
import com.example.revoluttestapp.core.mvi.CoreMviViewModel
import com.example.revoluttestapp.core.mvi.Reducer
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.exceptions.NoInternetConnectionException
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.Logger
import com.example.revoluttestapp.domain.utils.RxSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.ofType
import io.reactivex.rxjava3.kotlin.plusAssign
import java.util.*
import kotlin.collections.ArrayList

internal class CurrenciesViewModel(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val getFlagForCurrencyUseCase: GetFlagForCurrencyUseCase,
    private val updateCurrencySelectedCurrencyAndRates: UpdateCurrencySelectedCurrencyAndRates,
    private val convertMoneyUseCase: ConvertMoneyUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper,
    private val codeToCurrencyMapper: CodeToCurrencyMapper,
    private val currencyConverter: CurrencyConverter,
    private val updateCurrencyRateEverySecondUseCase: UpdateCurrencyRateEverySecondUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val rxSchedulers: RxSchedulers,
    private val logger: Logger
) : CoreMviViewModel<Action, State>() {
    private var shouldShowLoader = true
    private var isErrorShown = false
    private var blockUiWithLoaderUntilGettingUpdatedData = false
    private val reducer: Reducer<State, Change> = { state, change ->
        when (change) {
            is Change.ShowLoading -> state.copy(isLoaderShown = true, currencies = emptyList())
            is Change.ShowCurrencies -> state.copy(
                isLoaderShown = false,
                currencies = change.uiCurrencies,
                error = null
            )
            is Change.DoNothing -> state
            is Change.ShowError -> state.copy(isLoaderShown = false, error = change.throwable)
            is Change.ShowLoaderUntilGettingUpdatedData -> state.copy(
                isLoaderShown = true,
                currencies = listOf(),
                error = null
            )
        }
    }

    init {
        bindActions()
    }

    override val initialState: State
        get() = State.createEmpty()

    override fun bindActions() {
        val updateRatesEverySeconds = actions.ofType<Action.SubscribeOnCurrencyRates>()
            .switchMap {
                updateCurrencyRateEverySecondUseCase.execute()
                    .map<Change> { Change.DoNothing }
                    .doOnError { isErrorShown = true }
                    .doOnNext {
                        isErrorShown = false
                        blockUiWithLoaderUntilGettingUpdatedData = false
                    }
                    .onErrorReturn { Change.ShowError(it) }
                    .map {
                        if (blockUiWithLoaderUntilGettingUpdatedData) {
                            Change.ShowLoading
                        } else {
                            it
                        }
                    }
                //.startWith(Observable.just(Change.ShowLoading))
                //.filter { (shouldShowLoader && it is Change.ShowLoading) || it is Change.ShowError || it is Change.DoNothing }
            }.startWith(Observable.just(Change.ShowLoading))

        val cancelUpdatingRatesEverySecond = actions.ofType<Action.CancelUpdatingRates>()
            .doOnNext { shouldShowLoader = false }
            .switchMap {
                updateCurrencyRateEverySecondUseCase.unExecute(false)
                    .toObservable<Change>()
            }

        val amountOfMoneyChangedOfBaseCurrency = actions.ofType<Action.AmountOfMoneyChanged>()
            .map { it.amount }
            .distinctUntilChanged()
            .map { currencyRateUiMapper.mapAmountOfMoneyToDouble(it) }
            .flatMap { convertedAmount ->
                getSelectedCurrencyUseCase.execute()
                    .map { it.setAmount(convertedAmount) }
                    .flatMap {
                        saveCurrencyToMemoryUseCase.execute(it)
                            .andThen(convertMoneyUseCase.execute())
                            .andThen(Observable.just(Change.DoNothing))
                    }
            }

        val currenciesContent = actions.ofType<Action.LoadCurrencies>()
            .switchMap {
                getCurrencyRatesUseCase.execute()
                    .flatMap { rates ->
                        getSelectedCurrencyUseCase.execute()
                            .map { currencyConverter.convert(it, rates) }
                            .map { it.map { rate -> rate.currency } }
                            .flatMap { convertedCurrencies ->
                                loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies)
                            }.flatMap { uiConvertedCurrencies ->
                                loadFlagForSelectedCurrencyAndMapToUi()
                                    .map { uiConvertedCurrencies.addAtTheTop(it) }
                            }
                    }
                    .map<Change> { Change.ShowCurrencies(it) }
                    .onErrorReturn { Change.ShowError(it) }
            }

        val selectCurrency = actions.ofType<Action.SelectCurrency>()
            .map { it.uiCurrencyPlace }
            .flatMap { uiCurrency ->
                getSelectedCurrencyUseCase.execute()
                    .flatMap{ oldSelected->
                        if(oldSelected.getCode() == uiCurrency.currencyCode){
                            Observable.empty()
                        } else{
                            Observable.just(uiCurrency)
                        }
                    }

            }
            .map { uiCurrency ->
                val currency = codeToCurrencyMapper.map(uiCurrency.currencyCode)
                val amountOfMoney = currencyRateUiMapper.mapAmountOfMoneyToDouble(
                    uiCurrency.amountOfMoney
                )
                currency.setAmount(amountOfMoney)
            }
            .flatMap {
                updateCurrencySelectedCurrencyAndRates.execute(it)
                    .andThen(Observable.just(Change.DoNothing))
            }.map { if (isErrorShown) Change.ShowLoading else it }


        val changes = listOf(
            currenciesContent,
            selectCurrency,
            amountOfMoneyChangedOfBaseCurrency,
            updateRatesEverySeconds,
            cancelUpdatingRatesEverySecond
        )
        disposables += Observable.merge(changes)
            .scan(initialState, reducer)
            .distinctUntilChanged()
            .subscribeOn(rxSchedulers.io)
            .subscribe(state::accept, logger::logError)
    }

    private fun loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies: List<Currency>): Observable<ArrayList<UiCurrency>> {
        return Observable.fromIterable(convertedCurrencies)
            .flatMap { currency ->
                getFlagForCurrencyUseCase.execute(currency)
                    .map { flag -> currencyRateUiMapper.mapToUiCurrency(currency, flag) }
            }
            .collect({ ArrayList<UiCurrency>() }, { collection, item -> collection.add(item) })
            .toObservable()
    }

    private fun loadFlagForSelectedCurrencyAndMapToUi(): Observable<UiCurrency> {
        return getSelectedCurrencyUseCase.execute()
            .flatMap { selectedCurrency ->
                getFlagForCurrencyUseCase.execute(selectedCurrency)
                    .map { currencyRateUiMapper.mapToUiCurrency(selectedCurrency, it) }
            }
    }

    private fun List<UiCurrency>.addAtTheTop(currencyToConvertPlace: UiCurrency): List<UiCurrency> {
        val linkedList = LinkedList<UiCurrency>()
        linkedList.add(currencyToConvertPlace)
        linkedList.addAll(this)
        return linkedList
    }

    public override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}