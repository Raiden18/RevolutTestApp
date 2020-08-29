package com.example.revoluttestapp.currencyconverter.view.states

import android.view.View
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.currencyconverter.view.CurrenciesActivity
import com.example.revoluttestapp.core.mvi.ViewState
import kotlinx.android.synthetic.main.activity_main.*

internal class CurrenciesViewSates(
    currenciesActivity: CurrenciesActivity,
    private val currencies: List<UiCurrency>
): AbstractViewState(currenciesActivity) {


    override fun render() {
        hideLoader()
        hideErrorMessage()
        showCurrencies()
    }

   private fun showCurrencies(){
       currenciesRecyclerView.visibility = View.VISIBLE
       currenciesRecyclerView.updateItems(currencies)
   }
}