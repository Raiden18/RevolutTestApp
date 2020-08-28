package com.example.revoluttestapp.domain.models.countires

class Singapore : Country {
    companion object {
        const val CODE = "SG"
    }

    override val code: String
        get() = CODE
}