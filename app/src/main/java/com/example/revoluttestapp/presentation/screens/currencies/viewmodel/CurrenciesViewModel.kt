package com.example.revoluttestapp.presentation.screens.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.revoluttestapp.domain.usecases.GetCurrenciesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CurrenciesViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {

    init {
        getCurrenciesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.value.forEach {rate->
                    Log.i("HUI", rate.toString())
                }

            }, { Log.e("HUI", it.message.toString()) })
    }

}