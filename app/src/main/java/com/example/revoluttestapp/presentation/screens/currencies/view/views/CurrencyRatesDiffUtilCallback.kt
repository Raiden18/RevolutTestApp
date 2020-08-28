package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace

class CurrencyRatesDiffUtilCallback : DiffUtil.ItemCallback<UiCurrencyToConvertPlace>() {
    override fun areItemsTheSame(oldItem: UiCurrencyToConvertPlace, newItem: UiCurrencyToConvertPlace): Boolean {
        return oldItem.currencyCode == newItem.currencyCode
    }

    override fun getChangePayload(oldItem: UiCurrencyToConvertPlace, newItem: UiCurrencyToConvertPlace): Any? {
        val payloads = Bundle()
        if(oldItem.amountOfMoney != newItem.amountOfMoney){
            payloads.putString(CurrencyRatesAdapter.AMOUNT_OF_MONEY_PAYLOAD_KEY, newItem.amountOfMoney)
        }
        return payloads
    }


    override fun areContentsTheSame(oldItem: UiCurrencyToConvertPlace, newItem: UiCurrencyToConvertPlace): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}