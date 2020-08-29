package com.example.revoluttestapp.presentation.screens.currencies.view

import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrency
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrencyRateUiMapper

class CurrencyRateUiMapperImpl : CurrencyRateUiMapper {

    override fun mapAmountOfMoneyToDouble(amountOfMoney: String): Double {
        return if (amountOfMoney.isEmpty()) 0.0 else amountOfMoney.toDouble()
    }

    override fun mapToUiCurrency(currency: Currency, flag: Flag): UiCurrency {
        return UiCurrency(
            currency.getCode(),
            currency.getFullName(),
            currency.getAmount().toString(),
            flag.resId,
            false
        )
    }
}