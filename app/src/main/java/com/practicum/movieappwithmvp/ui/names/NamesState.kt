package com.practicum.movieappwithmvp.ui.names

import com.practicum.movieappwithmvp.domain.models.Person

sealed interface NamesState {

    object Loading : NamesState

    data class Content(
        val persons: List<Person>
    ) : NamesState

    data class Error(
        val message: String
    ) : NamesState

    data class Empty(
        val message: String
    ) : NamesState

}