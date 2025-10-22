package com.practicum.movieappwithmvp.di

import com.practicum.movieappwithmvp.presentation.movies.MoviesViewModel
import com.practicum.movieappwithmvp.presentation.cast.MoviesCastViewModel
import com.practicum.movieappwithmvp.presentation.poster.AboutViewModel
import com.practicum.movieappwithmvp.presentation.poster.PosterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesViewModel(get())
    }

    viewModel {(movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel {(posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

    viewModel { (movieId: String) ->
        MoviesCastViewModel(movieId, get())
    }

}