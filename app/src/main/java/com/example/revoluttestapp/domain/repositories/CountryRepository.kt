package com.example.revoluttestapp.domain.repositories

import com.example.revoluttestapp.domain.models.countires.Country
import io.reactivex.rxjava3.core.Observable

interface CountryRepository {
    fun getCountries(): Observable<List<Country>>
}