package com.practicum.movieappwithmvp.presentation.movies

import com.practicum.movieappwithmvp.domain.models.Movie

interface MoviesView {
    /*Мы упомянули, что для взаимодействия между UI и будущим Presenter нужен интерфейс View
    Интерфейс MoviesView — прослойка между слоями UI и Presentation. Добавьте его в пакет .presentation.movies.
    Попросите MoviesActivity реализовывать интерфейс MoviesView.
    Пока в интерфейсе нет методов, а значит, внутреннюю реализацию MoviesActivity менять не придётся.
     */

    fun showPlaceholderMessage(isVisible: Boolean)

    fun showMoviesList(isVisible: Boolean)

    fun showProgressBar(isVisible: Boolean)

    fun changePlaceholderText(newPlaceholderText: String)

    fun updateMoviesList(newMoviesList: List<Movie>)

    fun showToast(additionalMessage: String)
}