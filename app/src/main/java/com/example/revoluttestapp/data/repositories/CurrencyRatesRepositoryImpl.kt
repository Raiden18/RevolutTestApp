package com.example.revoluttestapp.data.repositories

import com.example.revoluttestapp.data.mappers.CurrencyRateMapper
import com.example.revoluttestapp.domain.models.currency.CurrencyRates
import com.example.revoluttestapp.domain.repositories.CurrencyRatesRepository
import io.reactivex.rxjava3.core.Observable

class CurrencyRatesRepositoryImpl(
    private val currencyRateMapper: CurrencyRateMapper,
    private val currencyRatesService: CurrencyRatesService
) : CurrencyRatesRepository {

    override fun getCurrencyFromApi(): Observable<CurrencyRates> {
        return currencyRatesService.getCurrencyRates()
            .map { currencyRateMapper.map(it) }
    }
}