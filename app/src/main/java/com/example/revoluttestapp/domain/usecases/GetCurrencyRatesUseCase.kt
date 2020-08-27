package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Euro
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Observable

class GetCurrencyRatesUseCase(
    private val currencyRatesService: CurrencyRatesRepository,
    private val currencyRepository: CurrencyRepository
) {

    fun execute(): Observable<List<CurrencyRate>> {
        return currencyRepository.getCurrentCurrencyFromMemory()
            .concatMap { currencyRatesService.getCurrencyRateFromApiFor(it) }
    }
}