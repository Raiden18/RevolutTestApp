package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.core.mvi.ViewState
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import kotlinx.android.synthetic.main.activity_main.*

internal abstract class AbstractViewState(private val currenciesActivity: CurrenciesActivity) :
    ViewState {
    protected val loader
        get() = currenciesActivity.currency_rates_loader_view!!
    protected val errorMessage
        get() = currenciesActivity.currency_rates_error_message!!
    protected val currenciesRecyclerView
        get() = currenciesActivity.currency_rates_recycler_view!!
    protected val cantSelectCurrencyMessage
        get() = currenciesActivity.currency_rates_selection_currency_message!!
    protected val cantSelectCurrencyButton
        get() = currenciesActivity.currency_rates_selection_currency_button!!

    protected fun hideErrorMessage() {
        errorMessage.visibility = View.GONE
        errorMessage.alpha = 0f
    }

    protected fun hideLoader() {
        loader.visibility = View.GONE
    }

    protected fun hideCurrencies() {
        currenciesRecyclerView.visibility = View.GONE
    }

    protected fun hideCantSelectCurrencyButton(){
        cantSelectCurrencyMessage.visibility = View.GONE
        cantSelectCurrencyButton.visibility = View.GONE
        cantSelectCurrencyMessage.alpha = 0f
        cantSelectCurrencyButton.alpha = 0f
    }

}