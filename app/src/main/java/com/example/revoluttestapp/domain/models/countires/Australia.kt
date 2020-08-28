package com.example.revoluttestapp.domain.models.countires

class Australia : Country {
    companion object {
        const val CODE = "AU"
    }

    override val code: String
        get() = CODE
}