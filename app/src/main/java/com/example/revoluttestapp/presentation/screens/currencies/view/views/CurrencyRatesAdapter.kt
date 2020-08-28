package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates.convertedCurrencyAdapterDelegate
import com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates.currencyToConvertAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CurrencyRatesAdapter(
    private val onCurrencyClick: (UiCurrencyPlace) -> Unit,
    onAmountOfMoneyChanged: (text: String) -> Unit
) : ListAdapter<UiCurrencyToConvertPlace, CurrencyViewHolder>(CurrencyRatesDiffUtilCallback()){
    companion object{
        const val AMOUNT_OF_MONEY_PAYLOAD_KEY = "AMOUNT_OF_MONEY_PAYLOAD_KEY"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_currency_rate, parent, false)
        return CurrencyViewHolder(onCurrencyClick, itemView)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}