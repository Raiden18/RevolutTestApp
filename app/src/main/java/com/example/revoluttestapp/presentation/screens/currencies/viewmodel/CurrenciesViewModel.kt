package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import com.example.revoluttestapp.domain.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.usecases.*
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.presentation.screens.core.mvi.CoreMviViewModel
import com.example.revoluttestapp.presentation.screens.core.mvi.Reducer
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
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
        val updateCurrencyRateEverySecond = updateCurrencyRateEverySecondUseCase.execute()
            .startWith(Observable.just(Change.DoNothing))

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
            .switchMapCompletable { saveCurrencyToMemoryUseCase.execute(it)}
            .andThen(Observable.just(Change.DoNothing))


        disposables += Observable.merge(
            currencies,
            updateCurrencyRateEverySecond,
            selectCurrency
        ).scan(initialState, reducer)
            .subscribeOn(rxSchedulers.io)
            .subscribe(state::accept, Timber::e)

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

    private fun setCurrencyToConvertToTopOfList(
        currencyToConvertPlace: UiCurrencyToConvertPlace,
        uiConvertedCurrencies: List<UiCurrencyToConvertPlace>
    ): List<UiCurrencyToConvertPlace> {
        val linkedList = LinkedList<UiCurrencyToConvertPlace>()
        linkedList.add(currencyToConvertPlace)
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