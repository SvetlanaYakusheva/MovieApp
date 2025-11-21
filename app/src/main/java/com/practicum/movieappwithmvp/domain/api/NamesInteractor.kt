package com.practicum.movieappwithmvp.domain.api

import com.practicum.movieappwithmvp.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface NamesInteractor {
//    fun searchNames(expression: String, consumer: NamesConsumer)
//
//    fun interface NamesConsumer {
//        fun consume(foundNames: List<Person>?, errorMessage: String?)
//    }

    fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>>
    /*
    Объекты, с которыми работает этот Flow, — это экземпляры класса Pair (пара),
    первый элемент которой — список найденных людей, а второй — сообщение об ошибке.
     */
}