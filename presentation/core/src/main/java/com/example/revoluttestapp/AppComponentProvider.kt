package com.example.revoluttestapp

import com.example.revoluttestapp.core.di.ApplicationComponent

interface AppComponentProvider {
    fun provideApplicationComponent(): ApplicationComponent
}