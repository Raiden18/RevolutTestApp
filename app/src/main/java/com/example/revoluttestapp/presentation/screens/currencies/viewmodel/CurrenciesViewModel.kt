package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.models.currency.CurrencyRate
import com.example.revoluttestapp.domain.usecases.GetCurrenciesUseCase
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyRate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
//TODO: Write tests
//TODO: Add error handling
class CurrenciesViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val currencyRateUiMapper: CurrencyRateUiMapper
) : ViewModel() {
    private val currencies = BehaviorSubject.create<List<UiCurrencyRate>>()

    init {
        getCurrenciesUseCase.execute()
            .map { currencyRateUiMapper.map(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("HUI", "UPDATE")
                currencies.onNext(it)

            }, { Log.e("HUI", it.message.toString()) })
    }

    fun getCurrencies(): Observable<List<UiCurrencyRate>> = currencies
}