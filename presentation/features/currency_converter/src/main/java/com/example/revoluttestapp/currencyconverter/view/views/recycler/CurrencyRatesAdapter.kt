package com.example.revoluttestapp.currencyconverter.view.views.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.revoluttestapp.currencyconverter.R
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.currencyconverter.view.views.edittext.CurrencyTextWatcher
import com.example.revoluttestapp.currencyconverter.view.views.edittext.EditTextFocusChangeListenerImpl

internal class CurrencyRatesAdapter(
    private val onCurrencyClick: (UiCurrency) -> Unit,
    private val onAmountOfMoneyChanged: (text: String) -> Unit
) : ListAdapter<UiCurrency, CurrencyViewHolder>(CurrencyRatesDiffUtilCallback()) {
    companion object {
        const val AMOUNT_OF_MONEY_PAYLOAD_KEY = "AMOUNT_OF_MONEY_PAYLOAD_KEY"
        const val TEXT_COLOR_OF_AMOUNT_OF_MONEY_PAYLOAD_KEY = "TEXT_COLOR_OF_AMOUNT_OF_MONEY_KEY"
    }

    private val currencyTextWatcher = CurrencyTextWatcher()
    private val editTextFocusChangeListener = EditTextFocusChangeListenerImpl(
        currencyTextWatcher,
        onAmountOfMoneyChanged
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_currency_rate, parent, false)
        return CurrencyViewHolder(onCurrencyClick, editTextFocusChangeListener, itemView)
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
        if (payloads.isNotEmpty()) {
            val bundle = payloads.first() as Bundle
            if (bundle.containsKey(AMOUNT_OF_MONEY_PAYLOAD_KEY)
                && bundle.containsKey(TEXT_COLOR_OF_AMOUNT_OF_MONEY_PAYLOAD_KEY)
            ) {
                val amount = bundle.getString(AMOUNT_OF_MONEY_PAYLOAD_KEY)!!
                val color = bundle.getInt(TEXT_COLOR_OF_AMOUNT_OF_MONEY_PAYLOAD_KEY)
                holder.updateAmountOfMoneyView(amount, color)
            }
        }else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }
}