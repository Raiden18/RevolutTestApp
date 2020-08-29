package com.example.revoluttestapp.currencyconverter.view.views.edittext

import android.text.Editable
import android.text.TextWatcher
import android.util.Log

internal class CurrencyTextWatcher: TextWatcher {
    var onTextChanged: ((String)-> Unit)? = null

    override fun afterTextChanged(p0: Editable?) = Unit

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (charSequence != null){
            convertToStringAndSendCallback(charSequence)
        }
    }

    private fun convertToStringAndSendCallback(charSequence: CharSequence){
        val textString = charSequence.toString()
        onTextChanged?.invoke(textString)
    }
}