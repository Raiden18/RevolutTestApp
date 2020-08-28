package com.example.revoluttestapp.presentation.screens.core.mvi

typealias Reducer<S, C> = (state: S, change: C) -> S