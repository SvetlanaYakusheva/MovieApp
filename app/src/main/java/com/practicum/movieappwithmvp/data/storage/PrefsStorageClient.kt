package com.practicum.movieappwithmvp.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.practicum.movieappwithmvp.data.StorageClient
import com.practicum.movieappwithmvp.domain.models.Movie
import java.lang.reflect.Type

class PrefsStorageClient<T>(
    private val context: Context,
    private val dataKey: String,
    private val type: Type) : StorageClient<T> {

    private val prefs: SharedPreferences = context.getSharedPreferences("MOVIES_SEARCH", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun storeData(data: T) {
        prefs.edit().putString(dataKey, gson.toJson(data, type)).apply()
    }

    override fun getData(): T? {
        val dataJson = prefs.getString(dataKey, null)
        if (dataJson == null) {
            return null
        } else {
            return gson.fromJson(dataJson, type)
        }
    }
}

interface SearchHistoryStorage {

    // Методы для добавления элемента в историю поиска, получения списка элементов истории поиска и очистки истории
    fun storeData(data: List<Movie>)
    fun getData(): List<Movie>?
}


class SharedPreferencesSearchHistoryStorage(
    private val prefs: SharedPreferences,
    private val gson: Gson) : SearchHistoryStorage {

    // Реализация методов, в которых происходит чтение и запись данных в SharedPreferences
    override fun storeData(data: List<Movie>) {
        //prefs.edit().putString(dataKey, gson.toJson(data, type)).apply()
    }

    override fun getData(): List<Movie>? {
        TODO("Not yet implemented")
    }

}