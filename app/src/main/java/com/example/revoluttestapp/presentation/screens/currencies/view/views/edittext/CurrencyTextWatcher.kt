package com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext

import android.text.Editable
import android.text.TextWatcher
import android.util.Log

class CurrencyTextWatcher: TextWatcher {
    var onTextChanged: ((String)-> Unit)? = null

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (text != null){
            val textString = text.toString()
            Log.i("HUI", textString)
            onTextChanged?.invoke(textString)
        }
    }
}