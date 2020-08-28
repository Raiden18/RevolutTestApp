package com.example.revoluttestapp.domain.models.countires

class Bulgaria: Country {
    companion object {
        const val CODE = "BG"
    }

    override val code: String
        get() = CODE
}