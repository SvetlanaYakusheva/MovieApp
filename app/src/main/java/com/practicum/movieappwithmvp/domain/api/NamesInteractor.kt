package com.practicum.movieappwithmvp.domain.api

import com.practicum.movieappwithmvp.domain.models.Person

interface NamesInteractor {
    fun searchNames(expression: String, consumer: NamesConsumer)

    fun interface NamesConsumer {
        fun consume(foundNames: List<Person>?, errorMessage: String?)
    }
}