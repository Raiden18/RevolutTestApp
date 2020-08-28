package com.example.revoluttestapp.domain.models.countires

class Indonesia: Country {
    companion object {
        const val CODE = "ID"
    }

    override val code: String
        get() = CODE
}