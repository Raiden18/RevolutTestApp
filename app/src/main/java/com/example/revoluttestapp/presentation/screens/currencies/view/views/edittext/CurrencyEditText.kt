package com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext.formatter.CurrencyEditTextFormatter

class CurrencyEditText(
    context: Context,
    attributeSet: AttributeSet
) : AppCompatEditText(context, attributeSet) {
    lateinit var textChanged: (String) -> Unit

    init {
        addTextChangedListener {
            if (it != null) {
                val editTextProxyImpl = EditTextProxyImpl(this)
                CurrencyEditTextFormatter(editTextProxyImpl).execute(it.toString())
            }
        }
    }

    fun enable(){
        isEnabled = true
        isFocusable = true
        requestFocus()
        isFocusableInTouchMode = true
    }

    fun disable(){
        isEnabled = false
        isFocusable = false
    }

}