package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currency.CurrencyRates
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import io.reactivex.rxjava3.core.Observable

class GetCurrenciesUseCase(
    private val currencyRatesService: CurrencyRatesRepository
) {

    fun execute(): Observable<CurrencyRates> {
        return currencyRatesService.getCurrencyFromApi()
    }
}