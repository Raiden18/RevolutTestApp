package com.example.revoluttestapp.presentation.screens.currencies.view.views.edittext.formatter

interface EditTextProxy {
    fun setText(text: String)
    fun setCursorToPosition(position: Int)
    fun setGrayTextColor()
    fun setBlackTextColor()
}