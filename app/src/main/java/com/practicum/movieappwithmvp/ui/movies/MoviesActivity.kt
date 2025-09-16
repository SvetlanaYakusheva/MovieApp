package com.practicum.movieappwithmvp.ui.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import com.practicum.movieappwithmvp.MoviesApplication
import com.practicum.movieappwithmvp.R
import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.presentation.movies.MoviesSearchPresenter
import com.practicum.movieappwithmvp.presentation.movies.MoviesView
import com.practicum.movieappwithmvp.ui.poster.PosterActivity
import com.practicum.movieappwithmvp.util.Creator
import moxy.MvpActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MoviesActivity :   Activity(), MoviesView {

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

    private var moviesSearchPresenter: MoviesSearchPresenter? = null

    private var textWatcher: TextWatcher? = null

    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var moviesList: RecyclerView
    private lateinit var progressBar: ProgressBar

    // Добавляем методы для изменения видимости UI-элементов

    override fun render(state: MoviesState) {
        when (state) {
            is MoviesState.Loading -> showLoading()
            is MoviesState.Content -> showContent(state.movies)
            is MoviesState.Error -> showError(state.errorMessage)
            is MoviesState.Empty -> showEmpty(state.message)
        }
    }

    private fun showLoading() {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showError(errorMessage: String) {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        placeholderMessage.text = errorMessage
    }

    private fun showEmpty(emptyMessage: String) {
        showError(emptyMessage)
    }

    private fun showContent(movies: List<Movie>) {
        moviesList.visibility = View.VISIBLE
        placeholderMessage.visibility = View.GONE
        progressBar.visibility = View.GONE

        adapter.movies.clear()
        adapter.movies.addAll(movies)
        adapter.notifyDataSetChanged()
    }

    override fun showToast(additionalMessage: String) {
        Toast.makeText(this, additionalMessage, Toast.LENGTH_LONG)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        moviesSearchPresenter = (this.applicationContext as? MoviesApplication)?.moviesSearchPresenter

        if (moviesSearchPresenter == null) {
            moviesSearchPresenter = Creator.provideMoviesSearchPresenter(
                moviesView = this,
                context = this,
            )
            (this.applicationContext as? MoviesApplication)?.moviesSearchPresenter = moviesSearchPresenter
        }

        // Кусочек кода, который был в Presenter
        placeholderMessage = findViewById(R.id.placeholderMessage)
        queryInput = findViewById(R.id.queryInput)
        moviesList = findViewById(R.id.locations)
        progressBar = findViewById(R.id.progressBar)

        moviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        moviesList.adapter = adapter

        queryInput.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    moviesSearchPresenter?.searchDebounce(
                        changedText = s?.toString() ?: "")
                }

                override fun afterTextChanged(s: Editable?) {
                }
            }
        )
        /*
        Чтобы минимизировать риск аварийного завершения работы приложения, рекомендуется:
        -использовать параметр s: CharSequence? для получения текста внутри TextWatcher;
        -отписывать TextWatcher от EditText в методе onDestroy у Activity.
        */
        textWatcher?.let { queryInput.addTextChangedListener(it) }
        //moviesSearchPresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        /*
       Чтобы минимизировать риск аварийного завершения работы приложения, рекомендуется:
       -использовать параметр s: CharSequence? для получения текста внутри TextWatcher;
       -отписывать TextWatcher от EditText в методе onDestroy у Activity.
       */
        textWatcher?.let { queryInput.removeTextChangedListener(it) }
        moviesSearchPresenter?.onDestroy()
    }

    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }
}