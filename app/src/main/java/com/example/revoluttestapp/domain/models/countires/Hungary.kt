package com.example.revoluttestapp.domain.models.countires

class Hungary: Country {
    companion object {
        const val CODE = "HU"
    }

    override val code: String
        get() = CODE
}