package com.example.revoluttestapp.domain.models.currencies

abstract class AbstractCurrency(
    protected val amountCount: Double
) : Currency {
    abstract val codeName: String

    override fun getAmount(): Double {
        return amountCount
    }

    override fun getCode(): String {
        return codeName
    }

    override fun getFullName(): String {
        return java.util.Currency.getInstance(codeName).displayName
    }
}