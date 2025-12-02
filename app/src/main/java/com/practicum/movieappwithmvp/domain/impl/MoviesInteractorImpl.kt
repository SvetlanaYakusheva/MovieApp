package com.practicum.movieappwithmvp.domain.impl

import android.util.Log
import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.domain.models.MovieCast
import com.practicum.movieappwithmvp.domain.models.MovieDetails
import com.practicum.movieappwithmvp.domain.models.Person
import com.practicum.movieappwithmvp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newSingleThreadExecutor()
//
//    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
//        Log.d("mycheck", "enter to MoviesInteractorImpl.searchMovies")
//        executor.execute {
//            when (val resource = repository.searchMovies(expression)) {
//                is Resource.Success -> {
//                    consumer.consume(resource.data, null)
//                }
//
//                is Resource.Error -> {
//                    consumer.consume(null, resource.message)
//                }
//            }
//        }
//
//    }
    override fun searchMovies(expression: String): Flow<Pair<List<Movie>?, String?>> {
        return repository.searchMovies(expression).map { result ->
            when(result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }
    override fun getMovieDetails(movieId: String): Flow<Pair<MovieDetails?, String?>> {
        return repository.getMovieDetails(movieId).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }

    override fun getMovieCast(movieId: String): Flow<Pair<MovieCast?, String?>> {
        return repository.getMovieCast(movieId).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }
}