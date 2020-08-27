package com.example.revoluttestapp.domain.models.currencies

data class RomanianLeu(private val amountCurrency: Double) : AbstractCurrency(amountCurrency) {
    companion object {
        const val CODE = "RON"
    }

    override fun setAmount(amount: Double): Currency {
        return copy(amountCurrency = amount)
    }

    override val codeName: String
        get() = CODE
}