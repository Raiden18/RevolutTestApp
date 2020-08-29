package com.example.domain.revoluttestapp.presentation.screens.core.mvi

typealias Reducer<S, C> = (state: S, change: C) -> S