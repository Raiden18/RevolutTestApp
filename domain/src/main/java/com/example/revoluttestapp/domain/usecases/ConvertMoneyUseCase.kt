package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.CurrencyConverter
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Completable

class ConvertMoneyUseCase(
    private val currencyRepository: CurrencyRepository,
    private val currencyConverter: CurrencyConverter,
    private val currencyRatesRepository: CurrencyRatesRepository
) {
    fun execute(): Completable {
        return  currencyRepository.getSelectedCurrencyFromMemory()
            .flatMap { selectedCurrency-> currencyRatesRepository.getCurrencyRateFromMemory()
                .take(1)
                .map { currencyConverter.convert(selectedCurrency, it) }
            }.switchMapCompletable { currencyRatesRepository.saveToMemory(it) }
    }
}