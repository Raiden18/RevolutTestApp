package com.example.revoluttestapp.domain.models.countires

class SouthAfrica: Country {
    companion object {
        const val CODE = "ZA"
    }

    override val code: String
        get() = CODE
}