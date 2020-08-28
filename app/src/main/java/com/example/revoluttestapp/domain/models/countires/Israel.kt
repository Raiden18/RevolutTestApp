package com.example.revoluttestapp.domain.models.countires

class Israel: Country {
    companion object {
        const val CODE = "IL"
    }

    override val code: String
        get() = CODE
}