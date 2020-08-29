package com.example.revoluttestapp.core.mvi

typealias Reducer<S, C> = (state: S, change: C) -> S