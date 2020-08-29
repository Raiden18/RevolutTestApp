package com.example.revoluttestapp.currencyconverter.view.views.edittext.formatter

internal interface EditTextProxy {
    fun setText(text: String)
    fun setCursorToPosition(position: Int)
    fun setGrayTextColor()
    fun setBlackTextColor()
}