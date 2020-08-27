package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace

class CurrencyRatesRecyclerView(
    context: Context,
    attributeSet: AttributeSet
) : RecyclerView(context, attributeSet) {
    lateinit var onCurrencyClick: (UiCurrencyPlace) -> Unit
    private val currencyRatesAdapter: CurrencyRatesAdapter by lazy {
        CurrencyRatesAdapter(onCurrencyClick)
    }

    init {
        layoutManager = LinearLayoutManager(context)
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