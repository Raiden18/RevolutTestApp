package com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates

import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

fun currencyToConvertAdapterDelegate(
) =
    adapterDelegateLayoutContainer<UiCurrencyToConvertPlace, UiCurrencyPlace>(R.layout.item_currency_rate) {

        bind {
            itemView.currency_rate_code.text = item.countryCode
            itemView.currency_rate_name.text = item.countryName
            itemView.currency_rate_amount_of_money.setText(item.amountOfMoney)
        }
    }