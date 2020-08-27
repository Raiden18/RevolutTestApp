package com.example.revoluttestapp.presentation.screens.currencies.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.core.AppComponentProvider
import com.example.revoluttestapp.presentation.screens.currencies.di.CurrenciesViewModelFactory
import com.example.revoluttestapp.presentation.screens.currencies.di.DaggerCurrenciesComponent
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrenciesViewModel
import com.trello.lifecycle4.android.lifecycle.AndroidLifecycle
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class CurrenciesActivity : AppCompatActivity() {
    private val provider = AndroidLifecycle.createLifecycleProvider(this)

    @Inject
    lateinit var currenciesViewModelFactory: CurrenciesViewModelFactory

    private val viewModel: CurrenciesViewModel by viewModels {
        currenciesViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currency_rates_recycler_view.onCurrencyClick = viewModel::selectCurrency
        currency_rates_recycler_view.onAmountOfMoneyChanged = viewModel::onAmountOfMoneyChanged
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.getCurrencies()
            .compose(provider.bindToLifecycle())
            .subscribe({
                currency_rates_recycler_view.updateItems(it)
            }, { Timber.e(it) })

    }

    private fun initDagger() {
        val applicationComponent =
            (application as AppComponentProvider).provideApplicationComponent()
        DaggerCurrenciesComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(this)
    }
}