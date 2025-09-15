package com.practicum.movieappwithmvp.presentation.movies

import com.practicum.movieappwithmvp.domain.models.Movie

interface MoviesView {
    /*Мы упомянули, что для взаимодействия между UI и будущим Presenter нужен интерфейс View
    Интерфейс MoviesView — прослойка между слоями UI и Presentation. Добавьте его в пакет .presentation.movies.
    Попросите MoviesActivity реализовывать интерфейс MoviesView.
    Пока в интерфейсе нет методов, а значит, внутреннюю реализацию MoviesActivity менять не придётся.
     */

    // Методы, меняющие внешний вид экрана
    // Состояние загрузки
    fun showLoading()

    // Состояние ошибки
    fun showError(errorMessage: String)

    // Состояние пустого списка
    fun showEmpty(emptyMessage: String)

    // Состояние контента
    fun showContent(movies: List<Movie>)

    // Методы «одноразовых событий»
    fun showToast(additionalMessage: String)
}