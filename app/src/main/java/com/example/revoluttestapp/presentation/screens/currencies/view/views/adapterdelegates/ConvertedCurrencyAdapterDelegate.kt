package com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates

import android.os.Bundle
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.view.views.CurrencyRatesAdapter.Companion.AMOUNT_OF_MONEY_PAYLOAD_KEY
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

fun convertedCurrencyAdapterDelegate(
    onCurrencyClick: (UiCurrencyPlace) -> Unit
) = adapterDelegateLayoutContainer<UiConvertedCurrency, UiCurrencyPlace>(
    R.layout.item_currency_rate
) {
    itemView.setOnClickListener {
        onCurrencyClick.invoke(item)
    }
    bind { payload ->
        if (payload.isEmpty()){
            itemView.currency_rate_code.text = item.countryCode
            itemView.currency_rate_name.text = item.countryName
            itemView.currency_rate_amount_of_money.setText(item.amountOfMoney)
            itemView.currency_rate_amount_of_money.isEnabled = false
            itemView.currency_rate_amount_of_money.isFocusable = false
            itemView.currency_rate_amount_of_money.movementMethod = null
            itemView.currency_rate_amount_of_money.keyListener = null
        } else{
            val bundlePayload = payload.first() as Bundle
            if (bundlePayload.getString(AMOUNT_OF_MONEY_PAYLOAD_KEY) != null){
                itemView.currency_rate_amount_of_money.setText(bundlePayload.getString(AMOUNT_OF_MONEY_PAYLOAD_KEY))
            }

        }

    }
}