package com.example.revoluttestapp.domain.models.currencies

data class PolishZloty(private val amountCurrency: Double) : AbstractCurrency(amountCurrency) {
    companion object {
        const val CODE = "PLN"
    }

    override fun setAmount(amount: Double): Currency {
        return copy(amountCurrency = amount)
    }

    override val codeName: String
        get() = CODE
}