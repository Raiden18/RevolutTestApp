package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Observable

//TODO: Add tests
class ConvertMoneyUseCase(
    private val currencyRepository: CurrencyRepository,
    private val currencyConverter: CurrencyConverter,
    private val currencyRatesRepository: CurrencyRatesRepository
) {
    fun execute(currency: Currency): Observable<List<Currency>> {
        return currencyRatesRepository.getCurrencyRateFromMemory()
            .map { currencyConverter.convert(currency, it) }

    }
}