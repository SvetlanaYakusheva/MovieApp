package com.practicum.movieappwithmvp.ui.cast

import com.practicum.movieappwithmvp.core.ui.RVItem
import com.practicum.movieappwithmvp.domain.models.MovieCast
import com.practicum.movieappwithmvp.presentation.cast.MoviesCastRVItem

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        // Поменяли тип ячеек на более общий
        val items: List<RVItem>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}