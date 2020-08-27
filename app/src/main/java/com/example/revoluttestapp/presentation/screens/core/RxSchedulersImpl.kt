package com.example.revoluttestapp.presentation.screens.core

import com.example.revoluttestapp.domain.utils.RxSchedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class RxSchedulersImpl: RxSchedulers {
    override val io: Scheduler
        get() = Schedulers.io()
    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val computation: Scheduler
        get() = Schedulers.computation()
}