package com.practicum.movieappwithmvp.domain.impl

import com.practicum.movieappwithmvp.domain.api.SearchHistoryInteractor
import com.practicum.movieappwithmvp.domain.api.SearchHistoryRepository
import com.practicum.movieappwithmvp.domain.models.Movie

class SearchHistoryInteractorImpl(
    private val repository: SearchHistoryRepository
) : SearchHistoryInteractor {

    override fun getHistory(consumer: SearchHistoryInteractor.HistoryConsumer) {
        consumer.consume(repository.getHistory().data)
    }

    override fun saveToHistory(m: Movie) {
        repository.saveToHistory(m)
    }
}