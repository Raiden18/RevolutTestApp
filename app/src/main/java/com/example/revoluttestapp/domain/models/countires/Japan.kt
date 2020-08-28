package com.example.revoluttestapp.domain.models.countires

class Japan: Country {
    companion object {
        const val CODE = "JP"
    }

    override val code: String
        get() = CODE
}