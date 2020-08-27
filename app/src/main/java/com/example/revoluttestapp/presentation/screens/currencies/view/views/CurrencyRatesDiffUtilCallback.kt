package com.example.revoluttestapp.presentation.screens.currencies.view.views

import androidx.recyclerview.widget.DiffUtil
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace

//TODO: SET UP
class CurrencyRatesDiffUtilCallback : DiffUtil.ItemCallback<UiCurrencyPlace>() {
    override fun areItemsTheSame(oldItem: UiCurrencyPlace, newItem: UiCurrencyPlace): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UiCurrencyPlace, newItem: UiCurrencyPlace): Boolean {
        return false
    }
}