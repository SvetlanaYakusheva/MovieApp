package com.practicum.movieappwithmvp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.movieappwithmvp.R
import com.practicum.movieappwithmvp.domain.models.Movie

class MovieViewHolder (parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.i_movie, parent, false)) {

    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)
    var cover: ImageView = itemView.findViewById(R.id.cover)

    fun bind(movie: Movie) {
        title.text = "${movie.title}"
        description.text = "${movie.description}"

        Glide.with(itemView.context)
            .load(movie.image)
            .centerCrop()
            .transform(RoundedCorners(12))
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(cover)

    }
}