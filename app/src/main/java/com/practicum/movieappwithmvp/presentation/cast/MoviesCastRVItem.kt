package com.practicum.movieappwithmvp.presentation.cast

import com.practicum.movieappwithmvp.core.ui.RVItem
import com.practicum.movieappwithmvp.domain.models.MovieCastPerson

sealed interface MoviesCastRVItem : RVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem

}