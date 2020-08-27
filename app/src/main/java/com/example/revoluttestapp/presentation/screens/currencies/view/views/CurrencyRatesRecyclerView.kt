package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace

class CurrencyRatesRecyclerView(
    context: Context,
    attributeSet: AttributeSet
) : RecyclerView(context, attributeSet) {
    lateinit var onCurrencyClick: (UiCurrencyPlace) -> Unit
    lateinit var onAmountOfMoneyChanged: (text: String) -> Unit
    lateinit var onHeaderHidden: () -> Unit
    private val currencyRatesAdapter: CurrencyRatesAdapter by lazy {
        CurrencyRatesAdapter(onCurrencyClick, onAmountOfMoneyChanged)
    }
    private val linearLayoutManager = LinearLayoutManager(context)
    init {
        layoutManager = linearLayoutManager
        addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (linearLayoutManager.findFirstVisibleItemPosition() != 0){
                    onHeaderHidden.invoke()
                }
            }
        })
    }

    fun updateItems(items: List<UiCurrencyPlace>) {
        setAdapter()
        currencyRatesAdapter.items = ArrayList<UiCurrencyPlace>(items)
    }

    private fun setAdapter() {
        if (adapter == null) {
            adapter = currencyRatesAdapter
        }
    }
}