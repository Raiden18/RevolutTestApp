package com.example.revoluttestapp.domain.models.countires

class Brazil: Country {
    companion object {
        const val CODE = "BZ"
    }

    override val code: String
        get() = CODE
}