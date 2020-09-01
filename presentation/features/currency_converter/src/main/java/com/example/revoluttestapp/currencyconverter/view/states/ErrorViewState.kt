package com.example.revoluttestapp.currencyconverter.view.states

import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.currencyconverter.view.animations.animateAppearanceWithAlphaIfItIsHidden

internal class ErrorViewState(
    currenciesActivity: CurrenciesActivity,
    private val throwable: Throwable?
) : AbstractViewState(currenciesActivity) {

    override fun render() {
        hideCurrencies()
        hideLoader()
        hideCantSelectCurrencyButton()
        showErrorMessage()
    }

    private fun showErrorMessage() {
        errorMessage.text = throwable?.message
        errorMessage.animateAppearanceWithAlphaIfItIsHidden()
    }
}