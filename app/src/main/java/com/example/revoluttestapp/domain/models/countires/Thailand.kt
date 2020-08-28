package com.example.revoluttestapp.domain.models.countires

class Thailand: Country {
    companion object {
        const val CODE = "TH"
    }

    override val code: String
        get() = CODE
}