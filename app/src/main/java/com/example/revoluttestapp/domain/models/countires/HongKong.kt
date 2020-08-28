package com.example.revoluttestapp.domain.models.countires

class HongKong: Country {
    companion object {
        const val CODE = "HK"
    }

    override val code: String
        get() = CODE
}