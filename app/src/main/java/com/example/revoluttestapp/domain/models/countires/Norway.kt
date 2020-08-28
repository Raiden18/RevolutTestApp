package com.example.revoluttestapp.domain.models.countires

class Norway : Country {
    companion object {
        const val CODE = "NO"
    }

    override val code: String
        get() = CODE
}