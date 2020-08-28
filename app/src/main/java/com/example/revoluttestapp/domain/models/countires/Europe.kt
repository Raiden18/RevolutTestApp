package com.example.revoluttestapp.domain.models.countires

class Europe: Country {
    companion object {
        const val CODE = "EU"
    }

    override val code: String
        get() = CODE
}