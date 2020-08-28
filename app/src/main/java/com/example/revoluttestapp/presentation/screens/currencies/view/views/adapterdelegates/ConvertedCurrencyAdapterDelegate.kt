package com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates

import android.os.Bundle
import com.bumptech.glide.Glide
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
    val amountOfMoneyEditText = itemView.currency_rate_amount_of_money
    itemView.setOnClickListener {
        onCurrencyClick.invoke(item)
    }
    bind { payload ->
        if (payload.isEmpty()){
            itemView.currency_rate_code.text = item.currencyCode
            itemView.currency_rate_name.text = item.countryName
            amountOfMoneyEditText.setText(item.amountOfMoney)
            Glide.with(itemView)
                .load(item.imageFlagUrl)
                .into(itemView.item_currency_rate_country_flag)
            amountOfMoneyEditText.isEnabled = false
            amountOfMoneyEditText.isFocusable = false
            amountOfMoneyEditText.movementMethod = null
            amountOfMoneyEditText.keyListener = null

        } else{
            val bundlePayload = payload.first() as Bundle
            if (bundlePayload.getString(AMOUNT_OF_MONEY_PAYLOAD_KEY) != null){
                amountOfMoneyEditText.setText(bundlePayload.getString(AMOUNT_OF_MONEY_PAYLOAD_KEY))
            }

        }

    }
}