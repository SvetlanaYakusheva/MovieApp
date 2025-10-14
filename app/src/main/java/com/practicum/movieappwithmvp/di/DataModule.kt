package com.practicum.movieappwithmvp.di

import org.koin.dsl.module
import android.content.Context
import com.google.gson.Gson
import com.practicum.movieappwithmvp.data.NetworkClient
import com.practicum.movieappwithmvp.data.network.IMDbApiService
import com.practicum.movieappwithmvp.data.network.RetrofitNetworkClient
import com.practicum.movieappwithmvp.data.storage.PrefsStorageClient
import com.practicum.movieappwithmvp.data.storage.SearchHistoryStorage
import com.practicum.movieappwithmvp.data.storage.SharedPreferencesSearchHistoryStorage
import org.koin.android.ext.koin.androidContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 val dataModule = module {
    single<IMDbApiService> {
        Retrofit.Builder()
            .baseUrl("https://tv-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDbApiService::class.java)
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single<SearchHistoryStorage> {
        SharedPreferencesSearchHistoryStorage(get(), get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

}
