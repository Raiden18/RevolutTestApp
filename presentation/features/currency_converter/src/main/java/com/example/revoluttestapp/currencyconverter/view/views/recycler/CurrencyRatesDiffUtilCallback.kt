package com.example.revoluttestapp.currencyconverter.view.views.recycler

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.revoluttestapp.currencyconverter.models.UiCurrency

internal class CurrencyRatesDiffUtilCallback : DiffUtil.ItemCallback<UiCurrency>() {
    override fun areItemsTheSame(oldItem: UiCurrency, newItem: UiCurrency): Boolean {
        return oldItem.currencyCode == newItem.currencyCode
    }

    override fun getChangePayload(oldItem: UiCurrency, newItem: UiCurrency): Any? {
        val payloads = Bundle()
        if (oldItem.amountOfMoney != newItem.amountOfMoney) {
            payloads.putString(
                CurrencyRatesAdapter.AMOUNT_OF_MONEY_PAYLOAD_KEY,
                newItem.amountOfMoney
            )
            payloads.putInt(
                CurrencyRatesAdapter.TEXT_COLOR_OF_AMOUNT_OF_MONEY_PAYLOAD_KEY,
                newItem.textColor
            )
        }
        return payloads
    }

    override fun areContentsTheSame(oldItem: UiCurrency, newItem: UiCurrency): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}