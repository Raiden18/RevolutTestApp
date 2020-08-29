package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.mvi.ViewState
import kotlinx.android.synthetic.main.activity_main.*

internal class LoaderViewState(
    private val currenciesActivity: CurrenciesActivity
) : ViewState {
    private val loader = currenciesActivity.currency_rates_loader_view
    private val errorMessage = currenciesActivity.currency_rates_error_message
    private val retryButton = currenciesActivity.retry_button

    override fun render() {
        loader.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
        retryButton.visibility = View.GONE
    }
}