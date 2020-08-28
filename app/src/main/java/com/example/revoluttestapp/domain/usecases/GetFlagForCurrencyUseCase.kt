package com.example.revoluttestapp.domain.usecases

import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.models.currencies.Currency
import com.example.revoluttestapp.domain.repositories.FlagRepository
import io.reactivex.rxjava3.core.Observable

class GetFlagForCurrencyUseCase(
    private val flagRepository: FlagRepository
) {

    fun execute(currency: Currency): Observable<Flag> {
        val countryCode = currency.getCode().take(2)
        return flagRepository.getFlagFor(countryCode)
    }
}