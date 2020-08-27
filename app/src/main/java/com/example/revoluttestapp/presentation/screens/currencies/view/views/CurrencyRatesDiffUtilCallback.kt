package com.example.revoluttestapp.presentation.screens.currencies.view.views

import androidx.recyclerview.widget.DiffUtil
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace

//TODO: SET UP
class CurrencyRatesDiffUtilCallback : DiffUtil.ItemCallback<UiCurrencyPlace>() {
    override fun areItemsTheSame(oldItem: UiCurrencyPlace, newItem: UiCurrencyPlace): Boolean {
        return oldItem == newItem
    }

    //TODO: set up pay loads
    override fun getChangePayload(oldItem: UiCurrencyPlace, newItem: UiCurrencyPlace): Any? {
        return Any()
    }

    override fun areContentsTheSame(oldItem: UiCurrencyPlace, newItem: UiCurrencyPlace): Boolean {
        return false
    }
}