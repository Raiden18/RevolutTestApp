package com.example.revoluttestapp.domain.models.countires

class GreatBritain: Country {
    companion object {
        const val CODE = "GB"
    }

    override val code: String
        get() = CODE
}