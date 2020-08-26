package com.example.revoluttestapp.data.repositories

import com.example.revoluttestapp.data.models.response.CurrencyResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CurrencyRatesService {
    @GET("latest?base=EUR")
    fun getCurrencyRates(): Observable<CurrencyResponse>
}