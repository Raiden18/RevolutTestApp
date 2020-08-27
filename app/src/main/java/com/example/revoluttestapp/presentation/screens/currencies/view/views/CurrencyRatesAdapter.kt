package com.example.revoluttestapp.presentation.screens.currencies.view.views

import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates.convertedCurrencyAdapterDelegate
import com.example.revoluttestapp.presentation.screens.currencies.view.views.adapterdelegates.currencyToConvertAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

//TODO: Payloads for updating data
class CurrencyRatesAdapter(
    onCurrencyClick: (UiCurrencyPlace) -> Unit,
    onAmountOfMoneyChanged: (String) -> Unit
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