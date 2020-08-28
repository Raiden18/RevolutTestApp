package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace

class CurrencyRatesDiffUtilCallback : DiffUtil.ItemCallback<UiCurrencyPlace>() {
    override fun areItemsTheSame(oldItem: UiCurrencyPlace, newItem: UiCurrencyPlace): Boolean {
        return oldItem.currencyCode == newItem.currencyCode
    }

    override fun getChangePayload(oldItem: UiCurrencyPlace, newItem: UiCurrencyPlace): Any? {
        val payloads = Bundle()
        if(oldItem.amountOfMoney != newItem.amountOfMoney){
            payloads.putString(CurrencyRatesAdapter.AMOUNT_OF_MONEY_PAYLOAD_KEY, newItem.amountOfMoney)
        }
        return payloads
    }


    override fun areContentsTheSame(oldItem: UiCurrencyPlace, newItem: UiCurrencyPlace): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}