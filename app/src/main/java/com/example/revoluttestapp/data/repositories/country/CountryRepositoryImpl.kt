package com.example.revoluttestapp.data.repositories.country

import com.example.revoluttestapp.domain.models.countires.*
import com.example.revoluttestapp.domain.repositories.CountryRepository
import io.reactivex.rxjava3.core.Observable

class CountryRepositoryImpl: CountryRepository {

    override fun getCountries(): Observable<List<Country>> {
        return Observable.just(listOf(
            Australia(),
            Brazil(),
            Bulgaria(),
            Canada(),
            China(),
            Croatia(),
            CzechRepublic(),
            Denmark(),
            Europe(),
            GreatBritain(),
            HongKong(),
            Hungary(),
            Iceland(),
            India(),
            Indonesia(),
            Israel(),
            Japan(),
            Malaysia(),
            Mexico(),
            NewZealand(),
            Norway(),
            Philippines(),
            Poland(),
            Romania(),
            Russia(),
            Singapore(),
            SouthAfrica(),
            SouthKorea(),
            Switzerland(),
            Thailand(),
            UnitedStates()
        ))
    }
}