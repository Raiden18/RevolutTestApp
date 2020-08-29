package com.example.revoluttestapp.core

import com.example.revoluttestapp.core.di.ApplicationComponent

interface AppComponentProvider {
    fun provideApplicationComponent(): ApplicationComponent
}