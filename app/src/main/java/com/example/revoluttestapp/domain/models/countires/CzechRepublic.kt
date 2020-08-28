package com.example.revoluttestapp.domain.models.countires

class CzechRepublic : Country {
    companion object {
        const val CODE = "CZ"
    }

    override val code: String
        get() = CODE
}