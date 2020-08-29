package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.core.mvi.ViewState
import kotlinx.android.synthetic.main.activity_main.*

class ErrorViewState(
    private val currenciesActivity: CurrenciesActivity,
    private val throwable: Throwable
): ViewState {
    private val loader = currenciesActivity.currency_rates_loader_view
    private val errorMessage = currenciesActivity.currency_rates_error_message
    private val retryButton = currenciesActivity.retry_button
    private val currenciesRecyclerView = currenciesActivity.currency_rates_recycler_view

    override fun render() {
        loader.visibility = View.GONE
        currenciesRecyclerView.visibility = View.GONE
        errorMessage.text = throwable.message
        errorMessage.visibility = View.VISIBLE
        retryButton.visibility = View.VISIBLE
    }
}