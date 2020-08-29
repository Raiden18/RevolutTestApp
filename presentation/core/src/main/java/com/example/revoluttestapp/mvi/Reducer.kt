package com.example.revoluttestapp.mvi

typealias Reducer<S, C> = (state: S, change: C) -> S