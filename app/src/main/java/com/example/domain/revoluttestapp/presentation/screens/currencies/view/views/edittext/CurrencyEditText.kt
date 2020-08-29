package com.example.domain.revoluttestapp.presentation.screens.currencies.view.views.edittext

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

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