package com.example.revoluttestapp.presentation.screens.currencies.view.views.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CurrencyRatesAdapter(
    onCurrencyClick: (UiCurrencyPlace) -> Unit,
    onAmountOfMoneyChanged: (text: String, cursorPosition: Int) -> Unit
) : AsyncListDifferDelegationAdapter<UiCurrencyPlace>(CurrencyRatesDiffUtilCallback()){
    companion object{
        const val AMOUNT_OF_MONEY_PAYLOAD_KEY = "AMOUNT_OF_MONEY_PAYLOAD_KEY"
    }
    init{
        delegatesManager
            .addDelegate(convertedCurrencyAdapterDelegate(onCurrencyClick))
            .addDelegate(currencyToConvertAdapterDelegate(onAmountOfMoneyChanged))
    }
}