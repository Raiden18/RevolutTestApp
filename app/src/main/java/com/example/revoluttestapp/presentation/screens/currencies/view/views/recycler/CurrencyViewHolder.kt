package com.example.revoluttestapp.presentation.screens.currencies.view.views.recycler

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

class CurrencyViewHolder(
    private val onCurrencyClick: (UiCurrencyPlace) -> Unit,
    private val onTextChanged: (String)-> Unit,
    itemView: View
) : RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View
        get() = itemView

    private val amountOfMoneyEditText = containerView.currency_rate_amount_of_money
    init{
        amountOfMoneyEditText.textChanged = onTextChanged
    }
    fun bind(item: UiCurrencyToConvertPlace) = with(containerView) {
        itemView.setOnClickListener {
            onCurrencyClick.invoke(item)
        }
        itemView.currency_rate_code.text = item.currencyCode
        itemView.currency_rate_name.text = item.countryName
        amountOfMoneyEditText.setText(item.amountOfMoney)
        amountOfMoneyEditText.setSelection(item.cursorIndex)
        if (itemView.item_currency_rate_country_flag.tag != item.imageFlagId){
            itemView.item_currency_rate_country_flag.setImageResource(item.imageFlagId)
            itemView.item_currency_rate_country_flag.tag = item.imageFlagId
        }
    }

    fun updateAmountOfMoneyView(amount: String){
        amountOfMoneyEditText.setText(amount)
    }

    fun initTextListener(){
        amountOfMoneyEditText.addTextChangedListener()
    }
}