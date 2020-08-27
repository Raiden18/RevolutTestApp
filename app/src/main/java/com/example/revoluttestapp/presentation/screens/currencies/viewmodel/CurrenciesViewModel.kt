package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.models.currency.CurrencyRate
import com.example.revoluttestapp.domain.usecases.GetCurrenciesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CurrenciesViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {
    private val currencies = BehaviorSubject.create<List<CurrencyRate>>()

    init {
        getCurrenciesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("HUI", "UPDATE")
                currencies.onNext(it.value)

            }, { Log.e("HUI", it.message.toString()) })
    }

    fun getCurrencies(): Observable<List<CurrencyRate>> = currencies
}