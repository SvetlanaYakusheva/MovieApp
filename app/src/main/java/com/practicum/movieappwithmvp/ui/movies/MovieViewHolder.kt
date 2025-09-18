package com.practicum.movieappwithmvp.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.movieappwithmvp.R
import com.practicum.movieappwithmvp.databinding.IMovieBinding
import com.practicum.movieappwithmvp.domain.models.Movie

class MovieViewHolder (private val binding: IMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(movie: Movie) {
        binding.title.text = "${movie.title}"
        binding.description.text = "${movie.description}"

        Glide.with(binding.root)
            .load(movie.image)
            .centerCrop()
            .transform(RoundedCorners(12))
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.cover)

    }

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = IMovieBinding.inflate(inflater, parent, false)
            return MovieViewHolder(binding)
        }
    }
}