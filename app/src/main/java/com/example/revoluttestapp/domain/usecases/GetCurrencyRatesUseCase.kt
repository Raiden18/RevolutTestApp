package com.example.revoluttestapp.domain.usecases

import android.util.Log
import com.example.revoluttestapp.domain.models.currencyrate.CurrencyRate
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import io.reactivex.rxjava3.core.Observable

class GetCurrencyRatesUseCase(
    private val currencyRatesService: CurrencyRatesRepository
) {

    fun execute(): Observable<List<CurrencyRate>> {
        return currencyRatesService.getCurrencyRateFromMemory()
    }
}