package com.example.revoluttestapp.data.repositories.flag

import android.content.Context
import com.example.revoluttestapp.domain.models.Flag
import com.example.revoluttestapp.domain.repositories.FlagRepository
import io.reactivex.rxjava3.core.Observable
import java.util.*

class FlagRepositoryImpl(
    private val context: Context
) : FlagRepository {

    override fun getFlagFor(countryCode: String): Observable<Flag> {
        val resId = context.resources.getIdentifier(
            countryCode.toLowerCase(Locale.US) + "_flag",
            "drawable",
            context.packageName
        )
        val flag = Flag(resId)
        return Observable.just(flag)
    }
}