package com.practicum.movieappwithmvp.presentation.movies

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.movieappwithmvp.R
import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.ui.movies.MoviesAdapter
import com.practicum.movieappwithmvp.ui.movies.MoviesState
import com.practicum.movieappwithmvp.util.Creator

class MoviesSearchPresenter(private val context: Context,
    ) {

    private var view: MoviesView? = null
    private var state: MoviesState? = null
    private var latestSearchText: String? = null

    fun attachView(view: MoviesView) {
        this.view = view
        state?.let { view.render(it) }
    }

    fun detachView() {
        this.view = null

    }

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val handler = Handler(Looper.getMainLooper())

    private val movies = ArrayList<Movie>()


    //private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        //val newSearchText = lastSearchText ?: ""
        val newSearchText = latestSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        this.latestSearchText = changedText
        //this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(MoviesState.Loading)

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
                                view?.showToast(errorMessage)
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
        this.state = state
        this.view?.render(state)
    }



    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }



    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }
}