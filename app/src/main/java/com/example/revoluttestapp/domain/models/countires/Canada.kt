package com.example.revoluttestapp.domain.models.countires

class Canada : Country {
    companion object {
        const val CODE = "CA"
    }

    override val code: String
        get() = CODE
}