package com.example.revoluttestapp.domain.models.currencies

import java.util.Currency as JavaCurrency

/**
 * This class has descriptor @param "code".
 * According to Polymorphism of GRASP patterns and "Effective Java" this is not good practice because this class represent every currency such as Rouble, Dollar, Euro etc.
 * So that this class should be an interface and this interface should be implemented for every specific currency. I followed this practice at first.
 * But when I finished project I realized that for this test project that practice doesn't make sense because every specific instance of specific currency has the same behavior.
 * And that best practice does nothing and I just had code that wasn't even used.
 * So that I make data class from Currency interface and got rid of implementations of that interface.
 */
data class Currency(
    val amount: Double,
    val code: String
) {
    private val javaCurrency = JavaCurrency.getInstance(code)

    val fullName: String
        get() = javaCurrency.displayName

    fun isCodesEquals(currency: Currency) = code == currency.code
}