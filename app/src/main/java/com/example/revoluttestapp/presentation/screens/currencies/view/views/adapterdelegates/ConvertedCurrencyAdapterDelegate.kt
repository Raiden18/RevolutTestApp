package com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates

import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

fun convertedCurrencyAdapterDelegate(
    onCurrencyClick: (UiCurrency) -> Unit
) = adapterDelegateLayoutContainer<UiConvertedCurrency, UiCurrencyPlace>(
    R.layout.item_currency_rate
) {

    bind {
        val uiCurrency = item.uiCurrency
        itemView.setOnClickListener {
            onCurrencyClick.invoke(item.uiCurrency)
        }
        itemView.currency_rate_code.text = uiCurrency.countryCode
        itemView.currency_rate_name.text = uiCurrency.countryName
    }
}