package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.currencyconverter.view.animations.animateAppearanceWithAlphaIfItIsHidden

internal class CurrenciesWitErrorViewState(
    currenciesActivity: CurrenciesActivity,
    private val throwable: Throwable,
    private val currencies: List<UiCurrency>
): AbstractViewState(currenciesActivity) {

    override fun render() {
        hideLoader()
        showCurrencies()
        hideCantSelectCurrencyButton()
        showErrorMessage()
    }

    private fun showCurrencies(){
        currenciesRecyclerView.visibility = View.VISIBLE
        currenciesRecyclerView.updateItems(currencies)
    }

    private fun showErrorMessage(){
        errorMessage.text = throwable.message
        errorMessage.animateAppearanceWithAlphaIfItIsHidden()
    }
}