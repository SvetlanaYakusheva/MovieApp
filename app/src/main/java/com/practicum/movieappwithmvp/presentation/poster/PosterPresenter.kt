package com.practicum.movieappwithmvp.presentation.poster

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.practicum.movieappwithmvp.R

class PosterPresenter (private val view: PosterView, private val imageUrl: String) {

    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}
