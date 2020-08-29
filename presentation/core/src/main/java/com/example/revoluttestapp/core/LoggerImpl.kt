package com.example.revoluttestapp.core

import android.util.Log
import com.example.revoluttestapp.domain.utils.Logger
import timber.log.Timber

internal class LoggerImpl: Logger {
    override fun logError(throwable: Throwable) {
        Timber.e(throwable)
    }

    override fun logText(tag: String, text: String) {
        Log.i(tag, text)
    }
}