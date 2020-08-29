package com.example.revoluttestapp.currencyconverter

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.revoluttestapp.currencyconverter.models.UiCurrency
import com.example.revoluttestapp.currencyconverter.viewmodel.CurrencyRateUiMapper
import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.models.currencies.Currency
import java.lang.Exception
import java.lang.IllegalStateException
import java.text.DecimalFormat

internal class CurrencyRateUiMapperImpl(
    private val context: Context
): CurrencyRateUiMapper {

    override fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double {
        return if (amountOfMoney.isEmpty()) 0.0 else amountOfMoney.toDouble()
    }

    override fun mapToUiCurrency(currency: Currency, flag: Flag): UiCurrency {
        val decimalFormat = DecimalFormat("#0.##")
        decimalFormat.maximumFractionDigits = 2
        val formattedAmount = decimalFormat.format(currency.getAmount())
        val textColor = if(formattedAmount == "0"){
            ContextCompat.getColor(context, android.R.color.darker_gray)
        } else{
            ContextCompat.getColor(context, android.R.color.black)
        }
        return UiCurrency(
            currency.getCode(),
            currency.getFullName(),
            formattedAmount,
            flag.resId,
            false,
            textColor
        )
    }
}