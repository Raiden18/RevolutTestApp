package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.presentation.screens.core.mvi.CoreMviViewModel
import com.example.revoluttestapp.presentation.screens.core.mvi.Reducer
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency
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
) : CoreMviViewModel<Action, State>() {

    private val reducer: Reducer<State, Change> = { state, change ->
        when (change) {
            is Change.ShowLoading -> state.copy(isLoaderShown = true)
            is Change.ShowCurrencies -> state.copy(
                isLoaderShown = false,
                currencies = change.uiCurrencies
            )
            is Change.DoNothing -> state
        }
    }

    init {
        bindActions()
    }

    override val initialState: State
        get() = State(isLoaderShown = false, currencies = emptyList())

    override fun bindActions() {
        updateCurrencyRateEverySecondUseCase.execute()
            .subscribeOn(rxSchedulers.io)
            .subscribe()

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
            }
            .switchMapCompletable { saveCurrencyToMemoryUseCase.execute(it) }
            .andThen(Observable.just(Change.DoNothing))


        disposables += Observable.merge(
            currencies,
            selectCurrency,
            amountOfMoneyChanged
        ).scan(initialState, reducer)
            .distinctUntilChanged()
            .doOnNext { Log.i("HUI", it.toString()) }
            .subscribeOn(rxSchedulers.io)
            .subscribe(state::accept, Timber::e)

    }

    private fun loadFlagsForConvertedCurrenciesAndMapToUi(convertedCurrencies: List<Currency>): Observable<ArrayList<UiCurrency>> {
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