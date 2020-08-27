package com.example.revoluttestapp.presentation.screens.currencies.view.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.R
import com.example.revoluttestapp.presentation.screens.currencies.models.UiCurrencyRate

//TODO: Payloads for updating data
class CurrencyRatesAdapter : RecyclerView.Adapter<CurrencyRatesViewHolder>(){
    private val items = arrayListOf<UiCurrencyRate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRatesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_currency_rate, parent, false)
        return CurrencyRatesViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CurrencyRatesViewHolder, position: Int) {
        val currencyRate = items[position]
        holder.bind(currencyRate)
    }

    fun updateItems(items: List<UiCurrencyRate>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}