package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.usecases.ConvertMoneyUseCase
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import com.example.revoluttestapp.domain.usecases.GetSelectedCurrencyUseCase
import com.example.revoluttestapp.domain.usecases.SaveCurrencyToMemoryUseCase
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

//TODO: Write tests
//TODO: Add error handling
//TODO: add composite disposables/
//TODO: ADD cache for currency
//TODO: GET RID OF COPY PASTS
class CurrenciesViewModel(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase,
    private val getSelectedCurrencyUseCase: GetSelectedCurrencyUseCase,
    private val saveCurrencyToMemoryUseCase: SaveCurrencyToMemoryUseCase,
    private val convertMoneyUseCase: ConvertMoneyUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper,
    private val codeToCurrencyMapper: CodeToCurrencyMapper,
    private val currencyConverter: CurrencyConverter,
    private val currencyRatesRepositor: CurrencyRatesRepository
) : ViewModel() {
    private val currencies = BehaviorRelay.create<List<UiCurrencyPlace>>()
    private var previousAmountOfMoney: String = ""
    private lateinit var currencyToChange: Currency

    init {
        subscribeOnRates()
        subscribeOnCurrencies()
    }

    fun selectCurrency(uiCurrency: UiCurrencyPlace) {
        val currency = codeToCurrencyMapper.map(uiCurrency.countryCode)
        val amountOfMoney =
            if (uiCurrency.amountOfMoney.isEmpty()) 0.0 else uiCurrency.amountOfMoney.toDouble()
        val currencyWithAmount = currency.setAmount(amountOfMoney)
        saveCurrencyToMemoryUseCase.execute(currencyWithAmount)
            .subscribe({}, { Timber.e(it) })
    }

    private fun subscribeOnRates() {
        getSelectedCurrencyUseCase.execute().switchMap { currency ->
            Observable.interval(1, TimeUnit.SECONDS).flatMap {
                currencyRatesRepositor.getCurrencyRateFromApiFor(currency)
            }
        }.subscribe()

    }

    private fun subscribeOnCurrencies() {
        getCurrencyRatesUseCase.execute()
            .flatMap { rates ->
                getSelectedCurrencyUseCase.execute()
                    .doOnNext { currencyToChange = it }
                    .map { selectedCurrency ->
                        val convertedCurrencies = currencyConverter.convert(selectedCurrency, rates)
                        currencyRateUiMapper.mapDomainToUi(selectedCurrency, convertedCurrencies)
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currencies.accept(it)
            }, { Timber.e(it) })
    }

    fun onAmountOfMoneyChanged(amountOfMoney: String) {
        if (previousAmountOfMoney == amountOfMoney) return
        previousAmountOfMoney = amountOfMoney
        val newCurrencyToChange = currencyToChange.setAmount(amountOfMoney.toDouble())
        saveCurrencyToMemoryUseCase.execute(newCurrencyToChange)
            .subscribe()
    }

    fun getCurrencies(): Observable<List<UiCurrencyPlace>> = currencies
}