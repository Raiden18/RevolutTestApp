package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Completable

class SaveCurrencyToMemoryUseCase(
    private val currencyRepository: CurrencyRepository
) {
    fun execute(currency: Currency): Completable {
        return currencyRepository.saveToMemoryCurrentCurrency(currency)
    }

}