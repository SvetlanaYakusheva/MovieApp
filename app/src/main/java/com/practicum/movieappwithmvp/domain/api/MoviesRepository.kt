package com.practicum.movieappwithmvp.domain.api


import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.domain.models.MovieDetails
import com.practicum.movieappwithmvp.domain.models.MovieCast
import com.practicum.movieappwithmvp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    //fun searchMovies(expression: String): Resource<List<Movie>>
    fun searchMovies(expression: String): Flow<Resource<List<Movie>>>

    //fun getMovieDetails(movieId: String): Resource<MovieDetails>
    fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>>

    //fun getMovieCast(movieId: String): Resource<MovieCast>
    fun getMovieCast(movieId: String): Flow<Resource<MovieCast>>
}