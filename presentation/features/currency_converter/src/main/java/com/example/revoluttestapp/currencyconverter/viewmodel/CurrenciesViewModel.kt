package com.example.revoluttestapp.currencyconverter.viewmodel

import android.util.Log
import com.example.revoluttestapp.mvi.CoreMviViewModel
import com.example.revoluttestapp.mvi.Reducer
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.ofType
import io.reactivex.rxjava3.kotlin.plusAssign
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

//TODO: Write tests
//TODO: Add error handling
//TODO: RETRY AFTER LOOSING INTERNET CONNECTION
internal class CurrenciesViewModel(
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
) : CoreMviViewModel<Action, State>() {

    private val reducer: Reducer<State, Change> = { state, change ->
        when (change) {
            is Change.ShowLoading -> state.copy(isLoaderShown = true, error = null)
            is Change.ShowCurrencies -> state.copy(
                isLoaderShown = false,
                currencies = change.uiCurrencies,
                error = null
            )
            is Change.DoNothing -> state
            is Change.ShowError -> state.copy(isLoaderShown = false, error = change.throwable)
        }
    }

    init {
        bindActions()
    }

    override val initialState: State
        get() = State(isLoaderShown = false, currencies = emptyList(), error = null)

    override fun bindActions() {
        val subscribeOnUpdatingRates: Observable<Change> =
            updateCurrencyRateEverySecondUseCase.execute()
                .toObservable<Change>()
                .map<Change> { Change.DoNothing }
                .onErrorReturn { Change.ShowError(it) }

        val amountOfMoneyChanged = actions.ofType<Action.AmountOfMoneyChanged>()
            .map { it.amount }
            .map { it.trim() }
            .distinctUntilChanged()
            .map { currencyRateUiMapper.mapAmountOfMoneyToDouble(it) }
            .switchMap { convertedAmount ->
                getSelectedCurrencyUseCase.execute()
                    .map { it.setAmount(convertedAmount) }
            }.switchMap {
                saveCurrencyToMemoryUseCase.execute(it)
                    .andThen(Observable.just(Change.DoNothing))
            }

        val currencies = actions.ofType<Action.LoadCurrencies>()
            .switchMap {
                getCurrencyRatesUseCase.execute()
                    .flatMap { rates ->
                        getSelectedCurrencyUseCase.execute()
                            .map { currencyConverter.convert(it, rates) }
                            .flatMap { convertedCurrencies ->
                                loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies)
                            }.flatMap { uiConvertedCurrencies ->
                                loadFlagForSelectedCurrencyAndMapToUi()
                                    .map {
                                        setCurrencyToConvertToTopOfList(
                                            it,
                                            uiConvertedCurrencies
                                        )
                                    }
                            }
                    }
                    .map<Change> { Change.ShowCurrencies(it) }
                    .onErrorReturn { Change.ShowError(it) }
                    .startWith(Observable.just(Change.ShowLoading))
            }

        val selectCurrency = actions.ofType<Action.SelectCurrency>()
            .map { it.uiCurrencyPlace }
            .map { uiCurrency ->
                val currency = codeToCurrencyMapper.map(uiCurrency.currencyCode)
                val amountOfMoney = currencyRateUiMapper.mapAmountOfMoneyToDouble(
                    uiCurrency.amountOfMoney
                )
                currency.setAmount(amountOfMoney)
            }.switchMapCompletable { saveCurrencyToMemoryUseCase.execute(it) }
            .andThen(Observable.just(Change.DoNothing))


        disposables += Observable.merge(
            currencies,
            selectCurrency,
            amountOfMoneyChanged,
            subscribeOnUpdatingRates
        ).scan(initialState, reducer)
            .distinctUntilChanged()
            .subscribeOn(rxSchedulers.io)
            .doOnNext { Log.i("HUI", it.toString()) }
            .subscribe(state::accept, Timber::e)
    }

    private fun loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies: List<com.example.revoluttestapp.domain.models.currencies.Currency>): Observable<ArrayList<UiCurrency>> {
        return Observable.fromIterable(convertedCurrencies)
            .flatMap { currency ->
                getFlagForCurrencyUseCase.execute(currency)
                    .map { flag ->
                        currencyRateUiMapper.mapToUiCurrency(
                            currency,
                            flag
                        )
                    }
            }.collect({ ArrayList<UiCurrency>() },
                { collection, item -> collection.add(item) })
            .toObservable()
    }

    private fun loadFlagForSelectedCurrencyAndMapToUi(): Observable<UiCurrency> {
        return getSelectedCurrencyUseCase.execute()
            .flatMap { selectedCurrency ->
                getFlagForCurrencyUseCase.execute(selectedCurrency)
                    .map { currencyRateUiMapper.mapToUiCurrency(selectedCurrency, it) }
                    .map { it.copy(isEditorEnabled = true) }
            }
    }

    private fun setCurrencyToConvertToTopOfList(
        currencyToConvertPlace: UiCurrency,
        uiConvertedCurrencies: List<UiCurrency>
    ): List<UiCurrency> {
        val linkedList = LinkedList<UiCurrency>()
        val currencyWithEnabledEditing = currencyToConvertPlace.copy(isEditorEnabled = true)
        linkedList.add(currencyWithEnabledEditing)
        uiConvertedCurrencies.forEach {
            if (currencyToConvertPlace.currencyCode != it.currencyCode) {
                linkedList.add(it)
            }
        }
        return linkedList
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}