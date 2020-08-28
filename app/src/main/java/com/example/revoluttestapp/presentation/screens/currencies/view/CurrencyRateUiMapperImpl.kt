package com.example.revoluttestapp.presentation.screens.currencies.view

import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiConvertedCurrency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper
import java.util.*

class CurrencyRateUiMapperImpl : CurrencyRateUiMapper {

    override fun mapCurrencyToConvert(currencyToConvert: Currency, flag: Flag): UiCurrencyToConvertPlace {
        val amountWithoutDot = currencyToConvert.getAmount()
            .toString()
            .replaceAfter(".", "")
            .replace(".", "")
        return UiCurrencyToConvertPlace(
            currencyToConvert.getCode(),
            currencyToConvert.getFullName(),
            amountWithoutDot,
            flag.imageUrl
        )
    }

    override fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double {
        return if (amountOfMoney.isEmpty()) 0.0 else amountOfMoney.toDouble()
    }

    override fun mapConvertedCurrencies(currency: Currency, flag: Flag): UiConvertedCurrency {
        return UiConvertedCurrency(
            currency.getCode(),
            currency.getFullName(),
            currency.getAmount().toString(),
            flag.imageUrl
        )
    }
}