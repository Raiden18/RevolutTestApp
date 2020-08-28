package com.example.revoluttestapp.presentation.screens.currencies.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.revoluttestapp.R
import com.example.revoluttestapp.domain.utils.RxSchedulers
import com.example.revoluttestapp.presentation.screens.core.AppComponentProvider
import com.example.revoluttestapp.presentation.screens.currencies.di.CurrenciesViewModelFactory
import com.example.revoluttestapp.presentation.screens.currencies.di.DaggerCurrenciesComponent
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.Action
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.CurrenciesViewModel
import com.example.revoluttestapp.presentation.screens.currencies.viewmodel.State
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

        }
        currency_rates_recycler_view.onHeaderHidden = {
            hideKeyboard()
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
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
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