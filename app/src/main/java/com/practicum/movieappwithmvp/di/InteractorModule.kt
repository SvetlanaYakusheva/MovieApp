package com.practicum.movieappwithmvp.di

import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.api.SearchHistoryInteractor
import com.practicum.movieappwithmvp.domain.impl.MoviesInteractorImpl
import com.practicum.movieappwithmvp.domain.impl.SearchHistoryInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

    single<SearchHistoryInteractor> {
        SearchHistoryInteractorImpl(get())
    }

}