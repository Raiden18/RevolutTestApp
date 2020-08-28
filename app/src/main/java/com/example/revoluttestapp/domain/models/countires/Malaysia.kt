package com.example.revoluttestapp.domain.models.countires

class Malaysia: Country {
    companion object {
        const val CODE = "MY"
    }

    override val code: String
        get() = CODE
}