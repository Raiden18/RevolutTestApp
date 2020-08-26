package com.example.revoluttestapp.presentation.application

import android.app.Application
import com.example.revoluttestapp.presentation.screens.core.AppComponentProvider
import com.example.revoluttestapp.presentation.screens.core.di.ApplicationComponent
import com.example.revoluttestapp.presentation.screens.core.di.DaggerApplicationComponent

class RevolutTestApplication : Application(), AppComponentProvider {
    private lateinit var applicationComponent: ApplicationComponent

    override fun provideApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .provideApplication(this)
            .build()
    }
}