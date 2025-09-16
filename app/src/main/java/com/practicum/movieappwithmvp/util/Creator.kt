package com.practicum.movieappwithmvp.util

import android.app.Activity
import android.content.Context
import com.practicum.movieappwithmvp.data.MoviesRepositoryImpl
import com.practicum.movieappwithmvp.data.network.RetrofitNetworkClient
import com.practicum.movieappwithmvp.domain.api.MoviesInteractor
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.domain.impl.MoviesInteractorImpl
import com.practicum.movieappwithmvp.presentation.movies.MoviesSearchPresenter
import com.practicum.movieappwithmvp.presentation.poster.PosterPresenter
import com.practicum.movieappwithmvp.presentation.movies.MoviesView
import com.practicum.movieappwithmvp.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(

        context: Context
    ): MoviesSearchPresenter {
        return MoviesSearchPresenter(

            context = context
        )
    }

    fun providePosterPresenter(view: PosterView, imageUrl: String): PosterPresenter {
        return PosterPresenter(view, imageUrl)
    }
}