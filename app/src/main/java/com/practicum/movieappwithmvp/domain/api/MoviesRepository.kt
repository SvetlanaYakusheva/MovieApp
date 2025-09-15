package com.practicum.movieappwithmvp.domain.api

import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}