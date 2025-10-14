package com.practicum.movieappwithmvp.domain.api

import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.util.Resource

interface SearchHistoryRepository {
    fun saveToHistory(m: Movie)
    fun getHistory(): Resource<List<Movie>>
}