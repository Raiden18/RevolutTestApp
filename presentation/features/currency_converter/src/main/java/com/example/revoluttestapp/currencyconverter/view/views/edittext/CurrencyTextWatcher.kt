package com.example.revoluttestapp.currencyconverter.view.views.edittext

import android.text.Editable
import android.text.TextWatcher
import android.util.Log

internal class CurrencyTextWatcher: TextWatcher {
    var onTextChanged: ((String)-> Unit)? = null

    override fun afterTextChanged(p0: Editable?) = Unit

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (text != null){
            val textString = text.toString()
            onTextChanged?.invoke(textString)
        }
    }
}