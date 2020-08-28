package com.example.revoluttestapp.domain.models.countires

class Mexico : Country {
    companion object {
        const val CODE = "MX"
    }

    override val code: String
        get() = CODE
}