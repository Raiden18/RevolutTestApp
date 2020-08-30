package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.core.mvi.ViewState
import kotlinx.android.synthetic.main.activity_main.*

internal class ErrorViewState(
    currenciesActivity: CurrenciesActivity,
    private val throwable: Throwable
): AbstractViewState(currenciesActivity) {

    override fun render() {
        hideLoader()
        hideCurrencies()
        showErrorMessage()
    }

    private fun showErrorMessage(){
        errorMessage.text = throwable.message
        errorMessage.visibility = View.VISIBLE
        retryButton.visibility = View.VISIBLE
    }
}