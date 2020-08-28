package com.example.revoluttestapp.domain.models.countires

class SouthKorea : Country {
    companion object {
        const val CODE = "KR"
    }

    override val code: String
        get() = CODE
}