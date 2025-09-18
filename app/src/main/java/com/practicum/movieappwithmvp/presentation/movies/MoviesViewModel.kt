package com.practicum.movieappwithmvp.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.practicum.movieappwithmvp.MoviesApplication
import com.practicum.movieappwithmvp.R
import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.ui.movies.MoviesState
import com.practicum.movieappwithmvp.util.Creator

class MoviesViewModel(private val context: Context): ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()

        fun getFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as MoviesApplication)
                MoviesViewModel(app)
            }
        }
    }

    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    private val stateLiveData = MutableLiveData<MoviesState>()
    fun observeState(): LiveData<MoviesState> = stateLiveData

    private val showToast = SingleLiveEvent<String?>()
    fun observeShowToast(): LiveData<String?> = showToast

    private var latestSearchText: String? = null

    private val handler = Handler(Looper.getMainLooper())

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        this.latestSearchText = changedText
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }

        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime,
        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(
                MoviesState.Loading
            )

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        val movies = mutableListOf<Movie>()
                        if (foundMovies != null) {
                            movies.addAll(foundMovies)
                        }

                        when {
                            errorMessage != null -> {
                                renderState(
                                    MoviesState.Error(
                                        errorMessage = context.getString(R.string.something_went_wrong),
                                    )
                                )
                                showToast.postValue(errorMessage)
                            }

                            movies.isEmpty() -> {
                                renderState(
                                    MoviesState.Empty(
                                        message = context.getString(R.string.nothing_found),
                                    )
                                )
                            }

                            else -> {
                                renderState(
                                    MoviesState.Content(
                                        movies = movies,
                                    )
                                )
                            }
                        }

                    }
                }
            })
        }
    }

    private fun renderState(state: MoviesState) {
        stateLiveData.postValue(state)
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }
}