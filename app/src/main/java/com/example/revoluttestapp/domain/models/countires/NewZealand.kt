package com.example.revoluttestapp.domain.models.countires

class NewZealand: Country {
    companion object {
        const val CODE = "NZ"
    }

    override val code: String
        get() = CODE
}