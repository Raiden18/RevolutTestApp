package com.example.revoluttestapp.currencyconverter

import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.currencyconverter.viewmodel.CurrencyRateUiMapper
import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.models.currencies.Currency
import java.lang.Exception
import java.lang.IllegalStateException
import java.text.DecimalFormat

internal class CurrencyRateUiMapperImpl : CurrencyRateUiMapper {

    override fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double {
        return if (amountOfMoney.isEmpty()) 0.0 else amountOfMoney.toDouble()
    }

    override fun mapToUiCurrency(currency: Currency, flag: Flag): UiCurrency {
        val amountString = currency.getAmount().toString()
        val decimalFormat = DecimalFormat("#")
        decimalFormat.maximumFractionDigits = 2
        val formattedAmount = try{
            decimalFormat.format(currency.getAmount())
        } catch (exeption: Exception){
            throw IllegalStateException()
        }

        return UiCurrency(
            currency.getCode(),
            currency.getFullName(),
            formattedAmount,
            flag.resId,
            false
        )
    }

    private fun String.isNumberContainsDot(): Boolean{
        return contains(".")
    }

    private fun String.isOnlyZerosAfterDot(): Boolean{
        return split(".").last().all { it == '0' }
    }

    private fun String.getNumbersBeforeDot(): String {
        return split(".").first()
    }
}