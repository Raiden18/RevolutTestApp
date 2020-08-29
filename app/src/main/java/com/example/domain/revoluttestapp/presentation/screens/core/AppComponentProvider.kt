package com.example.domain.revoluttestapp.presentation.screens.core

import com.example.domain.revoluttestapp.presentation.screens.core.di.ApplicationComponent

interface AppComponentProvider {
    fun provideApplicationComponent(): ApplicationComponent
}