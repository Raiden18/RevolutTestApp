package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.models.currencies.Euro
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import com.example.revoluttestapp.domain.repositories.CurrencyRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import java.util.concurrent.TimeUnit

class GetCurrencyRatesUseCase(
    private val currencyRatesService: CurrencyRatesRepository
) {

    fun execute(): Observable<List<CurrencyRate>> {
        return currencyRatesService.getCurencyRateFromMemory()
    }
}