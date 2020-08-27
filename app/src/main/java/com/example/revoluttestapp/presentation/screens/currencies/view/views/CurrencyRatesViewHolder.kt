package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

class CurrencyRatesViewHolder(
    itemView: View,
    private val onCurrencyClick: (UiCurrency) -> Unit
) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View?
        get() = itemView

    init {

    }

    //TODO: Change color of line of edit text
    fun bind(currency: UiCurrency) = with(itemView) {
        itemView.setOnClickListener {
            onCurrencyClick.invoke(currency)
        }
        currency_rate_code.text = currency.countryCode
        currency_rate_name.text = currency.countryName
    }
}