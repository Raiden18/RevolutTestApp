package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Observable

class GetSelectedCurrencyUseCase(
    private val currencyRepository: CurrencyRepository
) {
    fun execute(): Observable<Currency> {
        return currencyRepository.getCurrentCurrencyFromMemory()
            .distinctUntilChanged()
    }
}