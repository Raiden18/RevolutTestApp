package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates.convertedCurrencyAdapterDelegate
import com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates.currencyToConvertAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

//TODO: Payloads for updating data
class CurrencyRatesAdapter(
    private val onCurrencyClick: (UiCurrency) -> Unit
) : AsyncListDifferDelegationAdapter<UiCurrencyPlace>(CurrencyRatesDiffUtilCallback()){

    init{
        delegatesManager
            .addDelegate(convertedCurrencyAdapterDelegate(onCurrencyClick))
            .addDelegate(currencyToConvertAdapterDelegate())
    }
}