package com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
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
        val amountOfMoneyEditText = itemView.currency_rate_amount_of_money

        amountOfMoneyEditText.addTextChangedListener {
            if(amountOfMoneyEditText.isFocused){
                onAmountOfMoneyChanged.invoke(it.toString())
            }
        }
        amountOfMoneyEditText.setOnTouchListener { view, motionEvent ->
            itemView.currency_rate_amount_of_money.post {
                amountOfMoneyEditText.setSelection(amountOfMoneyEditText.text!!.length)
            }
            return@setOnTouchListener false
        }
        bind {

        }
    }