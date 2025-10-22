package com.practicum.movieappwithmvp.di

import org.koin.dsl.module

import com.practicum.movieappwithmvp.data.MoviesRepositoryImpl
import com.practicum.movieappwithmvp.data.SearchHistoryRepositoryImpl
import com.practicum.movieappwithmvp.data.converters.MovieCastConverter
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.domain.api.SearchHistoryRepository

val repositoryModule = module {

    factory { MovieCastConverter() }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }

    single<SearchHistoryRepository> {
        SearchHistoryRepositoryImpl(get())
    }
}