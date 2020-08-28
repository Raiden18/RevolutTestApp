package com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener

class CurrencyEditText(context: Context, attributeSet: AttributeSet): AppCompatEditText(context, attributeSet){
    lateinit var textChanged: (String)-> Unit

    init{
        addTextChangedListener{
            if(isFocusable){
                if(it.toString().isEmpty()){
                    setText("0")
                    textChanged.invoke(it.toString())
                }
            }
        }
    }

}