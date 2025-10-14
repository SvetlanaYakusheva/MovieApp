package com.practicum.movieappwithmvp.di

import org.koin.dsl.module

import com.practicum.movieappwithmvp.data.MoviesRepositoryImpl
import com.practicum.movieappwithmvp.data.SearchHistoryRepositoryImpl
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.domain.api.SearchHistoryRepository

val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }

    single<SearchHistoryRepository> {
        SearchHistoryRepositoryImpl(get())
    }
}