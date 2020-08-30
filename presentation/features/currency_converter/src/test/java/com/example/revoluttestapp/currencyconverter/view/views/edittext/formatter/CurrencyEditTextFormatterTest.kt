package com.example.revoluttestapp.currencyconverter.view.views.edittext.formatter

import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CurrencyEditTextFormatterTest {
    private lateinit var currencyEditTextFormatter: CurrencyEditTextFormatter
    private val editTextProxy: EditTextProxy = mockk(relaxed = true)
    private val onTextChanged: (String) -> Unit = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        clearMocks(editTextProxy)
        currencyEditTextFormatter = CurrencyEditTextFormatter(editTextProxy, onTextChanged)
    }

    @Test
    fun `Should set text 0 if edit text is empty`(){
        currencyEditTextFormatter.execute("")
        verify {
            editTextProxy.setText("0")
        }
    }

    @Test
    fun `Should let write onlu one 0 in a raw before comma`(){
        currencyEditTextFormatter.execute("00")
        currencyEditTextFormatter.execute("000")
        currencyEditTextFormatter.execute("0000")
        verify(exactly = 3) {
            editTextProxy.setText("0")
        }
    }

    @Test
    fun `Should set cursor at the end of edit text every time text is inputted`(){
        currencyEditTextFormatter.execute("12")
        verify(exactly = 1) {
            editTextProxy.setCursorToPosition(2)
        }
    }

    @Test
    fun `If edit text is empty should set to the first position`(){
        currencyEditTextFormatter.execute("")
        verify(exactly = 1) {
            editTextProxy.setCursorToPosition(1)
        }
    }

    @Test
    fun `if several zeros ara inputted coursor should be set at the first position`(){
        currencyEditTextFormatter.execute("00")
        verify(exactly = 1) {
            editTextProxy.setCursorToPosition(1)
        }
    }

    @Test
    fun `Should set cursor only three times`(){
        currencyEditTextFormatter.execute("0")
        currencyEditTextFormatter.execute("00")
        currencyEditTextFormatter.execute("123")
        verify(exactly = 3) {
            editTextProxy.setCursorToPosition(any())
        }
    }

    @Test
    fun `If it is int number it should remove zero before at the start of number`(){
        currencyEditTextFormatter.execute("03")
        verify(exactly = 1) {
            editTextProxy.setText("3")
        }
    }

    @Test
    fun `Should set gray text color if there is 0 number`(){
        currencyEditTextFormatter.execute("")
        currencyEditTextFormatter.execute("000")
        verify(exactly = 2) {
            editTextProxy.setGrayTextColor()
        }
    }

    @Test
    fun `Should set gray text color if there is not 0 number`(){
        currencyEditTextFormatter.execute("123")
        currencyEditTextFormatter.execute("001")
        verify(exactly = 2) {
            editTextProxy.setBlackTextColor()
        }
    }

    @Test
    fun `Should let write only two symbols after dot`(){
        currencyEditTextFormatter.execute("123.123")
        verify(exactly = 1) {
            editTextProxy.setText("123.12")
        }
    }

    @Test
    fun `Should send formatted callbacks for zero`(){
        currencyEditTextFormatter.execute("0")
        currencyEditTextFormatter.execute("0.0")
        verify(exactly = 2) {
            onTextChanged.invoke("0")
        }
    }
}