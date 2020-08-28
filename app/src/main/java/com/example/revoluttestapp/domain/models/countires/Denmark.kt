package com.example.revoluttestapp.domain.models.countires

class Denmark: Country {
    companion object {
        const val CODE = "DK"
    }

    override val code: String
        get() = CODE
}