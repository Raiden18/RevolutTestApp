package com.example.revoluttestapp.data.repositories.country

import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.repositories.FlagRepository
import io.reactivex.rxjava3.core.Observable

class FlagRepositoryImpl : FlagRepository {
    private companion object {
        const val COUNTRY_CODE_KEY = "countryCode"
        const val BASE_FLAG_URL = "https://www.countryflags.io/${COUNTRY_CODE_KEY}/shiny/64.png"
    }

    override fun getFlagFor(countryCode: String): Observable<Flag> {
        val flatUrl = BASE_FLAG_URL.replace(COUNTRY_CODE_KEY, countryCode)
        val flag= Flag(flatUrl)
        return Observable.just(flag)
    }
}