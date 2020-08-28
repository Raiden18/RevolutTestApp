package com.example.revoluttestapp.domain.models.countires

class Russia : Country {
    companion object {
        const val CODE = "RU"
    }

    override val code: String
        get() = CODE
}