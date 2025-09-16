package com.practicum.movieappwithmvp

import android.app.Application
import com.practicum.movieappwithmvp.presentation.movies.MoviesSearchPresenter

class MoviesApplication : Application() {

    var moviesSearchPresenter: MoviesSearchPresenter? = null

}