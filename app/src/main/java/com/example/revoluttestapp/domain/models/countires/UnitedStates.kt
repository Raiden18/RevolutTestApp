package com.example.revoluttestapp.domain.models.countires

class UnitedStates: Country {
    companion object {
        const val CODE = "US"
    }

    override val code: String
        get() = CODE
}