package com.practicum.movieappwithmvp.domain.api


import com.practicum.movieappwithmvp.domain.models.Person
import com.practicum.movieappwithmvp.util.Resource

interface NamesRepository {
    fun searchNames(expression: String): Resource<List<Person>>
}