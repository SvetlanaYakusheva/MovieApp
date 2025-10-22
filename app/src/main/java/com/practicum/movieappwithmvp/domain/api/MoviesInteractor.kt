package com.practicum.movieappwithmvp.domain.api


import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.domain.models.MovieDetails
import com.practicum.movieappwithmvp.domain.models.MovieCast

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    fun interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

    fun getMovieDetails(movieId: String, consumer: MovieDetailsConsumer)
    fun interface MovieDetailsConsumer {
        fun consume(movieDetails: MovieDetails?, errorMessage: String?)
    }

    fun getMovieCast(movieId: String, consumer: MovieCastConsumer)
    fun interface MovieCastConsumer {
        fun consume(movieCast: MovieCast?, errorMessage: String?)
    }
}