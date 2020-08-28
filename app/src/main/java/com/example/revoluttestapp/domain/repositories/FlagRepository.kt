package com.example.revoluttestapp.domain.repositories

import com.example.revoluttestapp.domain.models.Flag
import io.reactivex.rxjava3.core.Observable

interface FlagRepository {
    fun getFlagFor(countryCode: String): Observable<Flag>
}