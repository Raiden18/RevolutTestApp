package com.example.revoluttestapp.currencyconverter.view.views.edittext

import android.view.View
import com.example.revoluttestapp.currencyconverter.view.views.edittext.formatter.CurrencyEditTextFormatter

internal class EditTextFocusChangeListenerImpl(
    private val textWatcher: CurrencyTextWatcher,
    private val onTextChanged: (String) -> Unit
): View.OnFocusChangeListener {

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        view as CurrencyEditText
        if (hasFocus) {
            initTextWatcher(view)
        } else {
            disableTextWatcher(view)
        }
    }

    private fun initTextWatcher(currencyEditText: CurrencyEditText){
        currencyEditText.addTextChangedListener(textWatcher)
        textWatcher.onTextChanged = {
            val editTextProxy = EditTextProxyImpl(currencyEditText)
            CurrencyEditTextFormatter(editTextProxy).execute(it)
            onTextChanged.invoke(it)
        }
    }

    private fun disableTextWatcher(currencyEditText: CurrencyEditText){
        currencyEditText.removeTextChangedListener(textWatcher)
        textWatcher.onTextChanged = null
    }
}