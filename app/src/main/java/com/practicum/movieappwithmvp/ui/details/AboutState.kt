package com.practicum.movieappwithmvp.ui.details

import com.practicum.movieappwithmvp.domain.models.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}