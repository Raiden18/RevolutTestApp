package com.example.revoluttestapp.domain.models.countires

class China: Country {
    companion object {
        const val CODE = "CN"
    }

    override val code: String
        get() = CODE
}