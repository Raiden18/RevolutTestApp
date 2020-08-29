package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Completable

class ForceUpdateCurrencyRatesUseCase(
    private val currencyRepository: CurrencyRepository,
    private val currencyRatesRepository: CurrencyRatesRepository
) {
    fun execute(): Completable{
        return currencyRepository.getCurrentCurrencyFromMemory()
            .switchMap { currencyRatesRepository.getCurrencyRateFromApiFor(it.getCode()) }
            .switchMapCompletable { currencyRatesRepository.saveToMemory(it) }
    }
}