package com.example.revoluttestapp.presentation.screens.currencies.view.views.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyPlace
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyToConvertPlace
import com.example.revoluttestapp.presentation.screens.currencies.view.views.recycler.CurrencyRatesAdapter

class CurrencyRatesRecyclerView(
    context: Context,
    attributeSet: AttributeSet
) : RecyclerView(context, attributeSet) {
    lateinit var onCurrencyClick: (UiCurrencyPlace) -> Unit
    lateinit var onAmountOfMoneyChanged: (text: String) -> Unit
    lateinit var onHeaderHidden: () -> Unit
    private val currencyRatesAdapter: CurrencyRatesAdapter by lazy {
        CurrencyRatesAdapter(
            onCurrencyClick,
            onAmountOfMoneyChanged
        )
    }
    private val linearLayoutManager = LinearLayoutManager(context)

    init {
        layoutManager = linearLayoutManager
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val isHeaderOutOfScreen = linearLayoutManager.findFirstVisibleItemPosition() != 0
                if (isHeaderOutOfScreen) {
                    onHeaderHidden.invoke()
                }
            }
        })

    }

    fun updateItems(items: List<UiCurrencyToConvertPlace>) {
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