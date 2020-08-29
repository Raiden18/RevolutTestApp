package com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext.formatter.CurrencyEditTextFormatter

class CurrencyEditText(
    context: Context,
    attributeSet: AttributeSet
) : AppCompatEditText(context, attributeSet) {
    var textChanged: ((String) -> Unit)? = null

    init {

    }

    fun requestInput(){
        isEnabled = true
        isFocusable = true
        requestFocus()
        isFocusableInTouchMode = true
    }
}