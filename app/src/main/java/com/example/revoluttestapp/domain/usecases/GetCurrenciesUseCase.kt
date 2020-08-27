package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currency.CurrencyRates
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class GetCurrenciesUseCase(
    private val currencyRatesService: CurrencyRatesRepository
) {

    //TODO: Write UnitTest For ticker
    fun execute(): Observable<CurrencyRates> {
        return Observable.interval(1, TimeUnit.SECONDS)
            .flatMap { currencyRatesService.getCurrencyFromApi() }


    }
}