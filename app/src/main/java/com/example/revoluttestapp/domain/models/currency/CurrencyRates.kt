package com.example.revoluttestapp.domain.models.currency

data class CurrencyRates(
    val value: List<CurrencyRate>
) {
    companion object {
        fun creteEmpty(): CurrencyRates {
            return CurrencyRates(emptyList())
        }
    }
}