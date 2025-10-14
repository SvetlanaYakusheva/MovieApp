package com.practicum.movieappwithmvp.domain.api

import com.practicum.movieappwithmvp.domain.models.Movie

interface SearchHistoryInteractor {

    fun getHistory(consumer: HistoryConsumer)
    fun saveToHistory(m: Movie)

    interface HistoryConsumer {
        fun consume(searchHistory: List<Movie>?)
    }
}