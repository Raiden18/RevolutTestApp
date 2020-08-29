package com.example.revoluttestapp.domain.exceptions

import java.lang.Exception

class UnknownErrorException(val text: String?) : Exception(text)