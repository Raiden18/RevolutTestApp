package com.example.revoluttestapp.domain.models.countires

class Croatia: Country {
    companion object {
        const val CODE = "HR"
    }

    override val code: String
        get() = CODE
}