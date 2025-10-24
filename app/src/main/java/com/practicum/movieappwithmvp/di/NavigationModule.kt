package com.practicum.movieappwithmvp.di

import com.practicum.movieappwithmvp.core.navigation.Router
import com.practicum.movieappwithmvp.core.navigation.RouterImpl
import org.koin.dsl.module

val navigationModule = module {
    val router = RouterImpl()

    single<Router> { router }
    single { router.navigatorHolder }
}