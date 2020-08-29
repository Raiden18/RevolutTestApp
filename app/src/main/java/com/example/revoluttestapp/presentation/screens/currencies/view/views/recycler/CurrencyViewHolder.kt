package com.example.revoluttestapp.presentation.screens.currencies.view.views.recycler

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency
import com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext.CurrencyTextWatcher
import com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext.EditTextFocusChangeListenerImpl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

class CurrencyViewHolder(
    private val onCurrencyClick: (UiCurrency) -> Unit,
    private val onTextChanged: (String) -> Unit,
    itemView: View
) : RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View
        get() = itemView

    private val amountOfMoneyEditText = containerView.currency_rate_amount_of_money
    private val currencyCodeView = itemView.currency_rate_code
    private val currencyNameView = itemView.currency_rate_name
    private val countryFlagView = itemView.item_currency_rate_country_flag

    private val editTextTouchListener = View.OnTouchListener { _, _ ->
        itemView.performClick()
        false
    }

    init {
        val currencyTextWatcher = CurrencyTextWatcher()
        val editTextFocusChangeListener =
            EditTextFocusChangeListenerImpl(currencyTextWatcher, onTextChanged)
        amountOfMoneyEditText.onFocusChangeListener = editTextFocusChangeListener
    }

    fun bind(item: UiCurrency) = with(containerView) {
        itemView.setOnClickListener {
            onCurrencyClick.invoke(item)
        }
        currencyCodeView.text = item.currencyCode
        currencyNameView.text = item.currencyName
        amountOfMoneyEditText.setText(item.amountOfMoney)
        if (countryFlagView.tag != item.imageFlagId) {
            countryFlagView.setImageResource(item.imageFlagId)
            countryFlagView.tag = item.imageFlagId
        }
        if (item.isEditorEnabled) {
            amountOfMoneyEditText.requestInput()
            amountOfMoneyEditText.setOnTouchListener(null)
        } else {
            amountOfMoneyEditText.setOnTouchListener(editTextTouchListener)
        }
    }

    fun updateAmountOfMoneyView(amount: String) {
        amountOfMoneyEditText.setText(amount)
    }
}