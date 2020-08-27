package com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

@SuppressLint("ClickableViewAccessibility")
fun currencyToConvertAdapterDelegate(
    onAmountOfMoneyChanged: (text: String) -> Unit
) =
    adapterDelegateLayoutContainer<UiCurrencyToConvertPlace, UiCurrencyPlace>(R.layout.item_currency_rate) {
        itemView.currency_rate_amount_of_money.addTextChangedListener {
            onAmountOfMoneyChanged.invoke(it.toString())
        }
        itemView.currency_rate_amount_of_money.setOnTouchListener { view, motionEvent ->
            itemView.currency_rate_amount_of_money.post {
                itemView.currency_rate_amount_of_money.setSelection(itemView.currency_rate_amount_of_money.text!!.length)
            }
            return@setOnTouchListener false
        }
        bind {
            itemView.currency_rate_code.text = item.countryCode
            itemView.currency_rate_name.text = item.countryName
            itemView.currency_rate_amount_of_money.setText(item.amountOfMoney)
            itemView.currency_rate_amount_of_money.setSelection(item.cursorIndex)
        }
    }