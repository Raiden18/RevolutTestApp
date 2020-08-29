package com.example.revoluttestapp.presentation.screens.currencies.models

/**
 * Unfortunate the name "ViewModel" is already taken in Android Development world.
 * That's why I used "Ui" prefix instead of "ViewModel" postfix
 */
data class UiCurrency(
    val currencyCode: String,
    val countryName: String,
    val amountOfMoney: String,
    val imageFlagId: Int,
    val isEditorEnabled: Boolean
)