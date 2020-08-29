package com.example.revoluttestapp.domain.utils

interface Logger {
    fun logError(throwable: Throwable)
    fun logText(tag: String, text: String)
}