package com.practicum.movieappwithmvp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.models.MovieDetails
import com.practicum.movieappwithmvp.ui.details.AboutState

class AboutViewModel(private val movieId: String,
                     private val moviesInteractor: MoviesInteractor, ) : ViewModel() {


    val stateLiveData = MutableLiveData<AboutState>()
    fun observeState(): LiveData<AboutState> = stateLiveData

    init {
        moviesInteractor.getMovieDetails(movieId, object : MoviesInteractor.MovieDetailsConsumer {
        override fun consume(movieDetails: MovieDetails?, errorMessage: String?) {
            if (movieDetails != null) {
                stateLiveData.postValue(AboutState.Content(movieDetails))
            } else {
                stateLiveData.postValue(AboutState.Error(errorMessage ?: "Unknown error"))
            }
        }
    })
    }
}