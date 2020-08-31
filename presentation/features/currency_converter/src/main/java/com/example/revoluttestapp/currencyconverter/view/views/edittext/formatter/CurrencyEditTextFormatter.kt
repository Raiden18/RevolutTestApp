package com.example.revoluttestapp.currencyconverter.view.views.edittext.formatter

import android.util.Log
import java.text.DecimalFormat

/**
 * It was created only in oder to cover this logic with unit tests.
 * I don't want to make this project depends on robolectric because it is too slow to run to follow TDD approach.
 * That practice was taken from the "Effective Working With Legacy Code" book.
 */
internal class CurrencyEditTextFormatter(
    private val editTextProxy: EditTextProxy,
    private val onTextChanged: (String) -> Unit
) {
    private companion object {
        const val DEFAULT_EMPTY_VALUE = "0"
    }

    private var formattedString: String = ""

    fun execute(text: String) {
        formattedString = text
        if (text.isEmpty()) {
            setTextAndCursorToTheEndForZeroValue()
        } else if (text.length > 1 && text.isAllZeros()) {
            setTextAndCursorToTheEndForZeroValue()
        } else if (text.length > 1
            && text.isContainsDot().not()
            && text.isContainsZerosInTheBeginning()
        ) {
            clearZerosBeforeNumberAndSetBlackTextColor(text)
        } else if (text.isContainsDot() && text.numbersAfterDot() > 2) {
            roundDigitsAfterDot(text)
        } else {
            moveCursorToTheEndAndSetBlackTextColor(text)
        }
        onTextChanged.invoke(formattedString)
    }

    private fun String.isAllZeros(): Boolean {
        return all { it == '0' }
    }

    private fun String.isContainsZerosInTheBeginning(): Boolean {
        val firstNotZeroValue = indexOfFirst { it != '0' }
        return firstNotZeroValue > 0
    }

    private fun String.isContainsDot(): Boolean {
        return any { it == '.' }
    }

    private fun String.numbersAfterDot(): Int {
        return split(".").last().length
    }

    private fun clearZerosBeforeNumberAndSetBlackTextColor(text: String) {
        val firstNotZeroValue = text.indexOfFirst { it != '0' }
        val resultValue = text.removeRange(0, firstNotZeroValue)
        formattedString = resultValue
        editTextProxy.setText(resultValue)
        editTextProxy.setBlackTextColor()
    }

    private fun setTextAndCursorToTheEndForZeroValue() {
        formattedString = DEFAULT_EMPTY_VALUE
        editTextProxy.setText(DEFAULT_EMPTY_VALUE)
        editTextProxy.setCursorToPosition(DEFAULT_EMPTY_VALUE.length)
        editTextProxy.setGrayTextColor()
    }

    private fun roundDigitsAfterDot(text: String) {
        val numbersBeforeDot = text.split(".").first()
        val numberAfterDot = text.split(".").last()
        val twoNumbersAfterDots = numberAfterDot.take(2)
        val resultNumber = "$numbersBeforeDot.$twoNumbersAfterDots"
        formattedString = resultNumber
        editTextProxy.setText(resultNumber)
    }

    private fun moveCursorToTheEndAndSetBlackTextColor(text: String) {
        editTextProxy.setCursorToPosition(text.length)
        editTextProxy.setBlackTextColor()
    }
}