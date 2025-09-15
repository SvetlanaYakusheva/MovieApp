package com.practicum.movieappwithmvp.domain.impl

import android.util.Log
import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.util.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newSingleThreadExecutor()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        Log.d("mycheck", "enter to MoviesInteractorImpl.searchMovies")
        executor.execute {
            when (val resource = repository.searchMovies(expression)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }

    }
}