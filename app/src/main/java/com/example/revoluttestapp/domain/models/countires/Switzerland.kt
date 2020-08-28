package com.example.revoluttestapp.domain.models.countires

class Switzerland: Country {
    companion object {
        const val CODE = "CH"
    }

    override val code: String
        get() = CODE
}