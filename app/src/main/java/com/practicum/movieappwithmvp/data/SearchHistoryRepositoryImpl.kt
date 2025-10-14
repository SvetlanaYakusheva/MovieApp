package com.practicum.movieappwithmvp.data

import com.practicum.movieappwithmvp.domain.api.SearchHistoryRepository
import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.util.Resource

class SearchHistoryRepositoryImpl(
    private val storage: StorageClient<ArrayList<Movie>>): SearchHistoryRepository {

    override fun saveToHistory(m: Movie) {
        val movies = storage.getData() ?: arrayListOf()
        movies.add(m)
        storage.storeData(movies)
    }

    override fun getHistory(): Resource<List<Movie>> {
        val movies = storage.getData() ?: listOf()
        return Resource.Success(movies)
    }
}