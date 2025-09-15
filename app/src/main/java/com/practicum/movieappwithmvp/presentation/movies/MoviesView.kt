package com.practicum.movieappwithmvp.presentation.movies

import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.ui.movies.MoviesState

interface MoviesView {
    /*Мы упомянули, что для взаимодействия между UI и будущим Presenter нужен интерфейс View
    Интерфейс MoviesView — прослойка между слоями UI и Presentation. Добавьте его в пакет .presentation.movies.
    Попросите MoviesActivity реализовывать интерфейс MoviesView.
    Пока в интерфейсе нет методов, а значит, внутреннюю реализацию MoviesActivity менять не придётся.
     */

    // Методы, меняющие внешний вид экрана

    fun render(state: MoviesState)

    // Методы «одноразовых событий»
    fun showToast(additionalMessage: String)
}