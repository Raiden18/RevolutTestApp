package com.example.domain.revoluttestapp.presentation.screens.currencies.view.views.edittext

import android.view.View
import com.example.domain.revoluttestapp.presentation.screens.currencies.view.views.edittext.formatter.CurrencyEditTextFormatter

class EditTextFocusChangeListenerImpl(
    private val textWatcher: CurrencyTextWatcher,
    private val onTextChanged: (String) -> Unit
): View.OnFocusChangeListener {

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        view as CurrencyEditText
        if (hasFocus) {
            view.addTextChangedListener(textWatcher)
            textWatcher.onTextChanged = {
                val editTextProxy = EditTextProxyImpl(view)
                CurrencyEditTextFormatter(editTextProxy).execute(it)
                onTextChanged.invoke(it)
            }
        } else {
            view.removeTextChangedListener(textWatcher)
            textWatcher.onTextChanged = null
        }
    }
}