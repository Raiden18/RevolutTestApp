package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity

internal class ErrorViewState(
    currenciesActivity: CurrenciesActivity,
    private val throwable: Throwable?
) : AbstractViewState(currenciesActivity) {

    override fun render() {
        hideCurrencies()
        hideLoader()
        showErrorMessage()
    }

    private fun showErrorMessage() {
        errorMessage.text = throwable?.message
        errorMessage.visibility = View.VISIBLE
    }
}