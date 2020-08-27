package com.example.revoluttestapp.data.repositories.currencyrate

import com.example.revoluttestapp.data.models.response.CurrencyResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRatesService {
    @GET("latest")
    fun getCurrencyRates(@Query(value = "base") currencyCode: String): Observable<CurrencyResponse>
}