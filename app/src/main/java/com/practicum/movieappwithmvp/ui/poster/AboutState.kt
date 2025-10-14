package com.practicum.movieappwithmvp.ui.poster

import com.practicum.movieappwithmvp.domain.models.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}