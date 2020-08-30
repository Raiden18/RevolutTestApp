package com.example.revoluttestapp.currencyconverter.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.core.AppComponentProvider
import com.example.revoluttestapp.currencyconverter.R
import com.example.revoluttestapp.currencyconverter.di.CurrenciesViewModelFactory
import com.example.revoluttestapp.currencyconverter.di.DaggerCurrenciesComponent
import com.example.revoluttestapp.currencyconverter.view.states.CurrenciesViewSates
import com.example.revoluttestapp.currencyconverter.view.states.ErrorViewState
import com.example.revoluttestapp.currencyconverter.view.states.LoaderViewState
import com.example.revoluttestapp.currencyconverter.viewmodel.Action
import com.example.revoluttestapp.currencyconverter.viewmodel.CurrenciesViewModel
import com.example.revoluttestapp.currencyconverter.viewmodel.State
import com.example.revoluttestapp.core.mvi.ViewState
import com.example.revoluttestapp.domain.utils.Logger
import com.trello.lifecycle4.android.lifecycle.AndroidLifecycle
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class CurrenciesActivity : AppCompatActivity() {
    @Inject
    internal lateinit var currenciesViewModelFactory: CurrenciesViewModelFactory

    @Inject
    lateinit var rxSchedulers: RxSchedulers

    @Inject
    lateinit var logger: Logger
    private val provider = AndroidLifecycle.createLifecycleProvider(this)
    private val viewModel: CurrenciesViewModel by viewModels { currenciesViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initClickListeners()
        currency_rates_recycler_view.onAmountOfMoneyChanged = {
            viewModel.dispatch(Action.AmountOfMoneyChanged(it))
        }
        subscribeToViewModel()
    }

    private fun initClickListeners() {
        currency_rates_recycler_view.onCurrencyClick = {
            viewModel.dispatch(Action.SelectCurrency(it))
        }
        retry_button.setOnClickListener {
            viewModel.dispatch(Action.SubscribeOnCurrencyRates)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.dispatch(Action.SubscribeOnCurrencyRates)
        viewModel.dispatch(Action.LoadCurrencies)
    }
    override fun onStop() {
        super.onStop()
        viewModel.dispatch(Action.CancelUpdatingRates)
    }

    private fun subscribeToViewModel() {
        viewModel.observableState
            .observeOn(rxSchedulers.main)
            .compose(provider.bindToLifecycle())
            .subscribe(::renderState, logger::logError)
    }

    private fun renderState(state: State) {
        val viewState = createViewState(state)
        viewState.render()
    }

    private fun createViewState(state: State): ViewState {
        return with(state) {
            when {
                isLoaderShown -> LoaderViewState(this@CurrenciesActivity)
                error != null -> ErrorViewState(this@CurrenciesActivity, error)
                else -> CurrenciesViewSates(this@CurrenciesActivity, currencies)
            }
        }
    }

    private fun initDagger() {
        val applicationComponent = (application as AppComponentProvider)
            .provideApplicationComponent()
        DaggerCurrenciesComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(this)
    }
}