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
        if(errorMessage.alpha != 1f){
            errorMessage.visibility = View.VISIBLE
            errorMessage.alpha = 0f
            errorMessage.animate()
                .alpha(1f)
                .setDuration(300)
                .start()
        }
    }
}