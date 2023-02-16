package com.pidzama.moviefind.ui.screens.main.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.ItemMovieBinding

class SearchViewHolder (
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movieTitle.text = movie.name
        binding.rating.text = movie.rating.average.toString()
        binding.movieGenre.text = movie.genres.take(2).toString()
        Glide.with(itemView)
            .load(movie.image?.original)
            .into(binding.imageMovie)
    }
}