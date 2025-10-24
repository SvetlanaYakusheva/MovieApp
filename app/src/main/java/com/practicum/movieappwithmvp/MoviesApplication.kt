package com.practicum.movieappwithmvp

import android.app.Application
import com.practicum.movieappwithmvp.di.dataModule
import com.practicum.movieappwithmvp.di.interactorModule
import com.practicum.movieappwithmvp.di.navigationModule
import com.practicum.movieappwithmvp.di.repositoryModule
import com.practicum.movieappwithmvp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(dataModule,
                repositoryModule,
                interactorModule,
                viewModelModule,
                navigationModule)
        }
    }

}