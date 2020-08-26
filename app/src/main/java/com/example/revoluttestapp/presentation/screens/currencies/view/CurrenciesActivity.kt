package com.example.revoluttestapp.presentation.screens.currencies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.core.AppComponentProvider
import com.example.revoluttestapp.presentation.screens.currencies.CurrenciesViewModelFactory
import com.example.revoluttestapp.presentation.screens.currencies.di.DaggerCurrenciesComponent
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrenciesViewModel
import javax.inject.Inject

class CurrenciesActivity : AppCompatActivity() {
    @Inject
    lateinit var currenciesViewModelFactory: CurrenciesViewModelFactory

    private val viewModel: CurrenciesViewModel by viewModels{
        currenciesViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel
    }

    private fun initDagger(){
        val applicationComponent = (application as AppComponentProvider).provideApplicationComponent()
        DaggerCurrenciesComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(this)
    }
}