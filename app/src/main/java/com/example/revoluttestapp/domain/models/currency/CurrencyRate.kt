package com.example.revoluttestapp.domain.models.currency

data class CurrencyRate(
    val code: String,
    val rate: Double
) {
    companion object {
        fun createEmpty(): CurrencyRate {
            return CurrencyRate("", 0.toDouble())
        }

        fun createWithoutImage(
            shortName: String,
            rate: Double
        ) = CurrencyRate(shortName, rate)
    }

    fun isEmpty(): Boolean {
        return code.isEmpty() && rate == 0.toDouble()
    }

}