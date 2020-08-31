package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import io.reactivex.rxjava3.core.Observable

class GetCurrencyRatesUseCase(
    private val currencyRatesService: CurrencyRatesRepository
) {

    fun execute(): Observable<List<CurrencyRate>> {
        return currencyRatesService.getCurrencyRateFromMemory()
            .map { it.sortedBy { it.currency.code } }
    }
}