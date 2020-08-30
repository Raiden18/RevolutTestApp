package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.core.mvi.ViewState
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import kotlinx.android.synthetic.main.activity_main.*

internal class CurrenciesWitErrorViewState(
    currenciesActivity: CurrenciesActivity,
    private val throwable: Throwable,
    private val currencies: List<UiCurrency>
): AbstractViewState(currenciesActivity) {

    override fun render() {
        hideLoader()
        showCurrencies()
        showErrorMessage()
    }

    private fun showErrorMessage(){
        errorMessage.text = throwable.message
        errorMessage.visibility = View.VISIBLE
    }

    private fun showCurrencies(){
        currenciesRecyclerView.visibility = View.VISIBLE
        currenciesRecyclerView.updateItems(currencies)
    }
}