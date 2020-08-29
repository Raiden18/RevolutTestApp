package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.core.mvi.ViewState
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import kotlinx.android.synthetic.main.activity_main.*

internal abstract class AbstractViewState(currenciesActivity: CurrenciesActivity) : ViewState {
    protected val loader = currenciesActivity.currency_rates_loader_view!!
    protected val errorMessage = currenciesActivity.currency_rates_error_message!!
    protected val retryButton = currenciesActivity.retry_button!!
    protected val currenciesRecyclerView = currenciesActivity.currency_rates_recycler_view!!

    protected fun hideErrorMessage() {
        errorMessage.visibility = View.GONE
        retryButton.visibility = View.GONE
    }

    protected fun hideLoader() {
        loader.visibility = View.GONE
    }

    protected fun hideCurrencies() {
        currenciesRecyclerView.visibility = View.GONE
    }

}