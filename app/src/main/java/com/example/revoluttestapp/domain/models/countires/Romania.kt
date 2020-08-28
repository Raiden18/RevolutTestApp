package com.example.revoluttestapp.domain.models.countires

class Romania : Country {
    companion object {
        const val CODE = "RO"
    }

    override val code: String
        get() = CODE
}