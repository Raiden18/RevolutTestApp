package com.example.revoluttestapp.data.models.response

import com.google.gson.annotations.SerializedName

data class CurrencyRatesResponse(
    @SerializedName("AUD")
    val aUD: Double? = null,
    @SerializedName("BGN")
    val bGN: Double? = null,
    @SerializedName("BRL")
    val bRL: Double? = null,
    @SerializedName("CAD")
    val cAD: Double? = null,
    @SerializedName("CHF")
    val cHF: Double? = null,
    @SerializedName("CNY")
    val cNY: Double? = null,
    @SerializedName("CZK")
    val cZK: Double? = null,
    @SerializedName("DKK")
    val dKK: Double? = null,
    @SerializedName("GBP")
    val gBP: Double? = null,
    @SerializedName("HKD")
    val hKD: Double? = null,
    @SerializedName("HRK")
    val hRK: Double? = null,
    @SerializedName("HUF")
    val hUF: Double? = null,
    @SerializedName("IDR")
    val iDR: Double? = null,
    @SerializedName("ILS")
    val iLS: Double? = null,
    @SerializedName("INR")
    val iNR: Double? = null,
    @SerializedName("ISK")
    val iSK: Double? = null,
    @SerializedName("JPY")
    val jPY: Double? = null,
    @SerializedName("KRW")
    val kRW: Double? = null,
    @SerializedName("MXN")
    val mXN: Double? = null,
    @SerializedName("MYR")
    val mYR: Double? = null,
    @SerializedName("NOK")
    val nOK: Double? = null,
    @SerializedName("NZD")
    val nZD: Double? = null,
    @SerializedName("PHP")
    val pHP: Double? = null,
    @SerializedName("PLN")
    val pLN: Double? = null,
    @SerializedName("RON")
    val rON: Double? = null,
    @SerializedName("RUB")
    val rUB: Double? = null,
    @SerializedName("SEK")
    val sEK: Double? = null,
    @SerializedName("SGD")
    val sGD: Double? = null,
    @SerializedName("THB")
    val tHB: Double? = null,
    @SerializedName("USD")
    val uSD: Double? = null,
    @SerializedName("ZAR")
    val zAR: Double? = null
)