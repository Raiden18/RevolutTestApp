package com.example.revoluttestapp.domain.models.currencies

interface Currency {
    fun setAmount(amount: Double): Currency
    fun getAmount(): Double
    fun getCode(): String
    fun getFullName(): String
}