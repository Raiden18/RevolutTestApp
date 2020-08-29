package com.example.revoluttestapp.currencyconverter.view.views.edittext

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class CurrencyEditText(
    context: Context,
    attributeSet: AttributeSet
) : AppCompatEditText(context, attributeSet) {


    fun setText(text: String){
        super.setText(text)
        setSelection(text.length)
    }
}