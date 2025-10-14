package com.practicum.movieappwithmvp.util

import android.app.Activity
import android.content.Context
import com.google.gson.reflect.TypeToken
import com.practicum.movieappwithmvp.data.MoviesRepositoryImpl
import com.practicum.movieappwithmvp.data.SearchHistoryRepositoryImpl
import com.practicum.movieappwithmvp.data.network.RetrofitNetworkClient
import com.practicum.movieappwithmvp.data.storage.PrefsStorageClient
import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.domain.api.SearchHistoryInteractor
import com.practicum.movieappwithmvp.domain.api.SearchHistoryRepository
import com.practicum.movieappwithmvp.domain.impl.MoviesInteractorImpl
import com.practicum.movieappwithmvp.domain.impl.SearchHistoryInteractorImpl
import com.practicum.movieappwithmvp.domain.models.Movie
/*
object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    private fun getSearchHistoryRepository(context: Context): SearchHistoryRepository {
        return SearchHistoryRepositoryImpl(
            PrefsStorageClient<ArrayList<Movie>>(
            context,
            "HISTORY",
            object : TypeToken<ArrayList<Movie>>() {}.type)
        )
    }

    fun provideSearchHistoryInteractor(context: Context): SearchHistoryInteractor {
        return SearchHistoryInteractorImpl(getSearchHistoryRepository(context))
    }

}

 */