package com.practicum.movieappwithmvp.ui.movies

import com.practicum.movieappwithmvp.domain.models.Movie

sealed interface MoviesState {

    object Loading : MoviesState

    data class Content(
        val movies: List<Movie>
    ) : MoviesState

    data class Error(
        val message: String
    ) : MoviesState

    data class Empty(
        val message: String
    ) : MoviesState

}