package com.pidzama.moviefind.ui.main.home.adapters

import androidx.recyclerview.widget.DiffUtil
import com.pidzama.moviefind.data.model.movies.Movie

class MoviesUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}