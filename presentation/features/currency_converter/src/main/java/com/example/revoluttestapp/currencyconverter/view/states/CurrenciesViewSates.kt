package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.core.mvi.ViewState
import kotlinx.android.synthetic.main.activity_main.*

internal class CurrenciesViewSates(
    private val currenciesActivity: CurrenciesActivity,
    private val currencies: List<UiCurrency>
): ViewState {
    private val loader = currenciesActivity.currency_rates_loader_view
    private val currenciesRecyclerView = currenciesActivity.currency_rates_recycler_view
    private val errorMessage = currenciesActivity.currency_rates_error_message
    private val retryButton = currenciesActivity.retry_button

    override fun render() {
        loader.visibility = View.GONE
        currenciesRecyclerView.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
        retryButton.visibility = View.GONE
        currenciesRecyclerView.updateItems(currencies)
    }
}