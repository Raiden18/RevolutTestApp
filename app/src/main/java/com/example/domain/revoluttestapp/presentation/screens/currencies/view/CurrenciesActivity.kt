package com.example.domain.revoluttestapp.presentation.screens.currencies.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.revoluttestapp.R
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.domain.revoluttestapp.presentation.screens.core.AppComponentProvider
import com.example.domain.revoluttestapp.presentation.screens.currencies.di.CurrenciesViewModelFactory
import com.example.domain.revoluttestapp.presentation.screens.currencies.di.DaggerCurrenciesComponent
import com.example.domain.revoluttestapp.presentation.screens.currencies.viewmodel.Action
import com.example.domain.revoluttestapp.presentation.screens.currencies.viewmodel.CurrenciesViewModel
import com.example.domain.revoluttestapp.presentation.screens.currencies.viewmodel.State
import com.trello.lifecycle4.android.lifecycle.AndroidLifecycle
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class CurrenciesActivity : AppCompatActivity() {
    @Inject
    lateinit var currenciesViewModelFactory: CurrenciesViewModelFactory

    @Inject
    lateinit var rxSchedulers: RxSchedulers
    private val provider = AndroidLifecycle.createLifecycleProvider(this)
    private val viewModel: CurrenciesViewModel by viewModels { currenciesViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currency_rates_recycler_view.onCurrencyClick = {
            viewModel.dispatch(Action.SelectCurrency(it))
        }
        currency_rates_recycler_view.onAmountOfMoneyChanged = {
            viewModel.dispatch(Action.AmountOfMoneyChanged(it))
        }
        subscribeToViewModel()
        viewModel.dispatch(Action.LoadCurrencies)
    }

    private fun subscribeToViewModel() {
        viewModel.observableState
            .observeOn(rxSchedulers.main)
            .compose(provider.bindToLifecycle())
            .subscribe(::renderState, Timber::e)
    }

    private fun renderState(state: State) = with(state) {
        when {
            isLoaderShown -> {
                currency_rates_loader_view.visibility = View.VISIBLE
            }
            else -> {
                currency_rates_loader_view.visibility = View.GONE
                currency_rates_recycler_view.updateItems(currencies)
            }
        }
        Unit
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