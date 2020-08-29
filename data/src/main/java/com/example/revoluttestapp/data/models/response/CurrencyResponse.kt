package com.example.revoluttestapp.data.models.response

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("baseCurrency")
    val baseCurrency: String,
    @SerializedName("rates")
    val rates: HashMap<String, Double>
)

