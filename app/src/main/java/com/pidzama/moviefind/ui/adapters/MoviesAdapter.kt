package com.pidzama.moviefind.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.ItemMovieBinding

class MoviesAdapter(
    private val onClick: (movie: Movie) -> Unit
) : PagingDataAdapter<Movie, MoviesViewHolder>(MoviesUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onClick(movie)
            }
        }
    }
}