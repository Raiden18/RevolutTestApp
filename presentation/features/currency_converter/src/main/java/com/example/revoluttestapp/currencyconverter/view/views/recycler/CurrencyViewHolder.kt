package com.example.revoluttestapp.currencyconverter.view.views.recycler

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.currencyconverter.view.views.edittext.CurrencyTextWatcher
import com.example.revoluttestapp.currencyconverter.view.views.edittext.EditTextFocusChangeListenerImpl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

internal class CurrencyViewHolder(
    private val onCurrencyClick: (UiCurrency) -> Unit,
    editTextFocusChangeListener: EditTextFocusChangeListenerImpl,
    itemView: View
) : RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View
        get() = itemView

    private val amountOfMoneyEditText = containerView.currency_rate_amount_of_money
    private val currencyCodeView = itemView.currency_rate_code
    private val currencyNameView = itemView.currency_rate_name
    private val countryFlagView = itemView.item_currency_rate_country_flag

    @SuppressLint("ClickableViewAccessibility")
    private val editTextTouchListener = View.OnTouchListener { _, event ->
        itemView.onTouchEvent(event)
        false
    }

    init {
        amountOfMoneyEditText.onFocusChangeListener = editTextFocusChangeListener
    }

    fun bind(item: UiCurrency) = with(containerView) {
        itemView.setOnClickListener {
            amountOfMoneyEditText.requestFocus()
            val itemWithUpdatedAmount =
                item.copy(amountOfMoney = amountOfMoneyEditText.text!!.toString())
            onCurrencyClick.invoke(itemWithUpdatedAmount)
        }
        currencyCodeView.text = item.currencyCode
        currencyNameView.text = item.currencyName
        amountOfMoneyEditText.setText(item.amountOfMoney)
        if (countryFlagView.tag != item.imageFlagId) {
            countryFlagView.setImageResource(item.imageFlagId)
            countryFlagView.tag = item.imageFlagId
        }
        amountOfMoneyEditText.setOnTouchListener(editTextTouchListener)
        amountOfMoneyEditText.setTextColor(item.textColor)

    }

    fun updateAmountOfMoneyView(amount: String, color: Int) {
        amountOfMoneyEditText.setText(amount)
        amountOfMoneyEditText.setTextColor(color)
    }

}