package com.practicum.movieappwithmvp.util

import android.app.Activity
import android.content.Context
import com.practicum.movieappwithmvp.data.MoviesRepositoryImpl
import com.practicum.movieappwithmvp.data.network.RetrofitNetworkClient
import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }



}