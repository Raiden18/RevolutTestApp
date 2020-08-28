package com.example.revoluttestapp.domain.models.countires

class Philippines : Country {
    companion object {
        const val CODE = "PH"
    }

    override val code: String
        get() = CODE
}