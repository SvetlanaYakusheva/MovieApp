package com.practicum.movieappwithmvp.di

import com.practicum.movieappwithmvp.presentation.movies.MoviesViewModel
import com.practicum.movieappwithmvp.presentation.poster.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesViewModel(get())
    }

    viewModel { (posterUrl: String) ->
        DetailsViewModel(posterUrl)
    }

}