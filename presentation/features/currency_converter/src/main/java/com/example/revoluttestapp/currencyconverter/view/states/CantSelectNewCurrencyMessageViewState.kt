package com.example.revoluttestapp.currencyconverter.view.states

import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.currencyconverter.view.animations.animateAppearanceWithAlphaIfItIsHidden

internal class CantSelectNewCurrencyMessageViewState(
    currenciesActivity: CurrenciesActivity
) : AbstractViewState(currenciesActivity) {

    override fun render() {
        hideCurrencies()
        hideErrorMessage()
        hideLoader()
        showCantSelectCurrencyButton()
    }

    private fun showCantSelectCurrencyButton() {
        cantSelectCurrencyMessage.animateAppearanceWithAlphaIfItIsHidden()
        cantSelectCurrencyButton.animateAppearanceWithAlphaIfItIsHidden()
    }
}