package com.example.revoluttestapp.domain.models.countires

class India: Country {
    companion object {
        const val CODE = "IN"
    }

    override val code: String
        get() = CODE
}