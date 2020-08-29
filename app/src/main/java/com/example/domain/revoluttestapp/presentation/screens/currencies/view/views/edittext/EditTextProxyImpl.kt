package com.example.domain.revoluttestapp.presentation.screens.currencies.view.views.edittext

import androidx.core.content.ContextCompat
import com.example.domain.revoluttestapp.presentation.screens.currencies.view.views.edittext.formatter.EditTextProxy

class EditTextProxyImpl(private val currencyEditText: CurrencyEditText): EditTextProxy {
    override fun setText(text: String) {
        currencyEditText.setText(text)
    }

    override fun setCursorToPosition(position: Int) {
        currencyEditText.setSelection(position)
    }

    override fun setGrayTextColor() {
        val grayTextColor = ContextCompat.getColor(currencyEditText.context, android.R.color.darker_gray)
        currencyEditText.setTextColor(grayTextColor)
    }

    override fun setBlackTextColor() {
        val blackTextColor = ContextCompat.getColor(currencyEditText.context, android.R.color.black)
        currencyEditText.setTextColor(blackTextColor)
    }
}