package com.example.revoluttestapp.currencyconverter.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Unfortunately, the name "ViewModel" is already taken in Android Development world.
 * That's why I used "Ui" prefix instead of "ViewModel" postfix
 */
internal data class UiCurrency(
    val currencyCode: String,
    val currencyName: String,
    val amountOfMoney: String,
    val imageFlagId: Int,
    val textColor: Int
)