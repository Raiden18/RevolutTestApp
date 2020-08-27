package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.models.CodeToCurrencyMapper
import com.example.revoluttestapp.domain.models.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.usecases.ConvertMoneyUseCase
import com.example.revoluttestapp.domain.usecases.GetCurrencyRatesUseCase
import com.example.revoluttestapp.domain.usecases.GetSelectedCurrencyUseCase
import com.example.revoluttestapp.domain.usecases.SaveCurrencyToMemoryUseCase
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

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
    private val currencyConverter: CurrencyConverter
) : ViewModel() {
    private val currencies = BehaviorRelay.create<List<UiCurrencyPlace>>()
    private var previousAmountOfMoney: String = ""
    private lateinit var currencyToChange: Currency

    init {
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

    private fun subscribeOnCurrencies() {
        getSelectedCurrencyUseCase.execute()
            .doOnNext { currencyToChange = it }
            .switchMap { selectedCurrency ->
                getCurrencyRatesUseCase.execute(selectedCurrency)
                    .map { currencyConverter.convert(selectedCurrency, it) }
                    .map { currencyRateUiMapper.mapDomainToUi(selectedCurrency, it) }
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