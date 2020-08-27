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
    lateinit var onAmountOfMoneyChanged: (String) -> Unit

    private val currencyRatesAdapter: CurrencyRatesAdapter by lazy {
        CurrencyRatesAdapter(onCurrencyClick, onAmountOfMoneyChanged)
    }

    init {
        layoutManager =  LinearLayoutManager(context)

    }

    fun updateItems(items: List<UiCurrencyPlace>) {
        setAdapter()
        currencyRatesAdapter.items = ArrayList<UiCurrencyPlace>(items)
    }

    private fun setAdapter() {
        if (adapter == null) {
            adapter = currencyRatesAdapter
            currencyRatesAdapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
                override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                    smoothScrollToPosition(0)
                }

                override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                    smoothScrollToPosition(0)
                }

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    smoothScrollToPosition(0)
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                    smoothScrollToPosition(0)
                }
            })
        }
    }
}