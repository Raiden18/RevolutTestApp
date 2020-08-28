package com.example.revoluttestapp.domain.models.countires

class Poland: Country {
    companion object {
        const val CODE = "PL"
    }

    override val code: String
        get() = CODE
}