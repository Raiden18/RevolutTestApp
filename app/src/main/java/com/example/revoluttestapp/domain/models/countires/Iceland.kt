package com.example.revoluttestapp.domain.models.countires

class Iceland: Country {
    companion object {
        const val CODE = "IS"
    }

    override val code: String
        get() = CODE
}