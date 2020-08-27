package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.domain.models.currency.CurrencyRate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

class CurrencyRatesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View?
        get() = itemView

    fun bind(currencyRate: CurrencyRate) = with(itemView){
        currency_rate_code.text = currencyRate.code
    }
}