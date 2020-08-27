package com.example.revoluttestapp.domain.utils

import io.reactivex.rxjava3.core.Scheduler

interface RxSchedulers {
    val io: Scheduler
    val main: Scheduler
    val computation: Scheduler
}