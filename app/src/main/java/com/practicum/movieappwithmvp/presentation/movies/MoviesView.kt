package com.practicum.movieappwithmvp.presentation.movies

import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.ui.movies.MoviesState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MoviesView {
    /*Мы упомянули, что для взаимодействия между UI и будущим Presenter нужен интерфейс View
    Интерфейс MoviesView — прослойка между слоями UI и Presentation. Добавьте его в пакет .presentation.movies.
    Попросите MoviesActivity реализовывать интерфейс MoviesView.
    Пока в интерфейсе нет методов, а значит, внутреннюю реализацию MoviesActivity менять не придётся.
     */


    fun render(state: MoviesState)


    fun showToast(additionalMessage: String)
}