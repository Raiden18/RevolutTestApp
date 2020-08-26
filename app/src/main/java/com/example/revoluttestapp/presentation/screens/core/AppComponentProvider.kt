package com.example.revoluttestapp.presentation.screens.core

import com.example.revoluttestapp.presentation.screens.core.di.ApplicationComponent

interface AppComponentProvider {
    fun provideApplicationComponent(): ApplicationComponent
}