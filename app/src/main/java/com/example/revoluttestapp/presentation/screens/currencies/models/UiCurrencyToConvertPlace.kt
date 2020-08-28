package com.example.revoluttestapp.presentation.screens.currencies.models

data class UiCurrencyToConvertPlace(
    override val currencyCode: String,
    override val countryName: String,
    override val amountOfMoney: String,
    override val imageFlagId: Int
) : UiCurrencyPlace{
    var cursorIndex = amountOfMoney.length
}