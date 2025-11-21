package com.practicum.movieappwithmvp.domain.api


import com.practicum.movieappwithmvp.domain.models.Person
import com.practicum.movieappwithmvp.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {
    //fun searchNames(expression: String): Resource<List<Person>>
    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}