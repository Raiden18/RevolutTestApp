package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.core.mvi.ViewState
import kotlinx.android.synthetic.main.activity_main.*

internal class LoaderViewState(
    currenciesActivity: CurrenciesActivity
) : AbstractViewState(currenciesActivity) {

    override fun render() {
        showLoader()
        hideCurrencies()
        hideErrorMessage()
    }

    private fun showLoader(){
        loader.visibility = View.VISIBLE
    }
}