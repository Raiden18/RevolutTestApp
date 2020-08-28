package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

class CurrencyViewHolder(
    private val onCurrencyClick: (UiCurrencyPlace) -> Unit,
    itemView: View
): RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View
        get() = itemView

    private val amountOfMoneyEditText = containerView.currency_rate_amount_of_money

    fun bind(item: UiCurrencyToConvertPlace) = with(containerView){
        itemView.setOnClickListener {
            onCurrencyClick.invoke(item)
        }
        if(item.currencyCode != itemView.currency_rate_code.text){
            itemView.currency_rate_code.text = item.currencyCode
        }
        if(item.countryName != itemView.currency_rate_name.text){
            itemView.currency_rate_name.text = item.countryName
        }
        if (amountOfMoneyEditText.text.toString() != item.amountOfMoney){
            amountOfMoneyEditText.setText(item.amountOfMoney)
        }
        amountOfMoneyEditText.setSelection(item.cursorIndex)
        itemView.item_currency_rate_country_flag.setImageResource(item.imageFlagId)
    }
}