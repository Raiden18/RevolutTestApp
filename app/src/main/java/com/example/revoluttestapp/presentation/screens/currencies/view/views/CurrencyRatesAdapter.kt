package com.example.revoluttestapp.presentation.screens.currencies.view.views

import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates.convertedCurrencyAdapterDelegate
import com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates.currencyToConvertAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

//TODO: Payloads for updating data
class CurrencyRatesAdapter(
    private val onCurrencyClick: (UiCurrencyPlace) -> Unit
) : AsyncListDifferDelegationAdapter<UiCurrencyPlace>(CurrencyRatesDiffUtilCallback()){

    init{
        delegatesManager
            .addDelegate(convertedCurrencyAdapterDelegate(onCurrencyClick))
            .addDelegate(currencyToConvertAdapterDelegate())
    }
}