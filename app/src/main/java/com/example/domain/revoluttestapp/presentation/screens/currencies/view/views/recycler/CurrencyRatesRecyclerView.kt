package com.example.domain.revoluttestapp.presentation.screens.currencies.view.views.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.revoluttestapp.presentation.screens.currencies.models.UiCurrency

class CurrencyRatesRecyclerView(
    context: Context,
    attributeSet: AttributeSet
) : RecyclerView(context, attributeSet) {
    lateinit var onCurrencyClick: (UiCurrency) -> Unit
    lateinit var onAmountOfMoneyChanged: (text: String) -> Unit
    private val currencyRatesAdapter: CurrencyRatesAdapter by lazy {
        CurrencyRatesAdapter(
            onCurrencyClick,
            onAmountOfMoneyChanged
        )
    }
    private val linearLayoutManager = LinearLayoutManager(context)

    init {
        layoutManager = linearLayoutManager
    }

    fun updateItems(items: List<UiCurrency>) {
        setAdapter()
        currencyRatesAdapter.submitList(items)
    }

    private fun setAdapter() {
        if (adapter == null) {
            adapter = currencyRatesAdapter
            currencyRatesAdapter.registerAdapterDataObserver(object :
                RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    scrollToPosition(0)
                }

                override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                    scrollToPosition(0)
                }
            })
        }
    }
}