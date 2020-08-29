package com.example.domain.revoluttestapp.presentation.screens.currencies.view.views.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.revoluttestapp.R
import com.example.domain.revoluttestapp.presentation.screens.currencies.models.UiCurrency

class CurrencyRatesAdapter(
    private val onCurrencyClick: (UiCurrency) -> Unit,
    private val onAmountOfMoneyChanged: (text: String) -> Unit
) : ListAdapter<UiCurrency, CurrencyViewHolder>(CurrencyRatesDiffUtilCallback()) {
    companion object {
        const val AMOUNT_OF_MONEY_PAYLOAD_KEY = "AMOUNT_OF_MONEY_PAYLOAD_KEY"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_currency_rate, parent, false)

        return CurrencyViewHolder(onCurrencyClick, onAmountOfMoneyChanged, itemView)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onBindViewHolder(
        holder: CurrencyViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isNotEmpty()) {
            val bundle = payloads.first() as Bundle
            if (bundle.containsKey(AMOUNT_OF_MONEY_PAYLOAD_KEY)) {
                val amount = bundle.getString(AMOUNT_OF_MONEY_PAYLOAD_KEY)!!
                holder.updateAmountOfMoneyView(amount)
            }
        }
    }
}