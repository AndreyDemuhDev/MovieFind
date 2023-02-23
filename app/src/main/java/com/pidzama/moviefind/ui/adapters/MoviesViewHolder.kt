package com.pidzama.moviefind.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.ItemMovieBinding

class MoviesViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movieTitle.text = movie.name
        binding.rating.text = movie.rating.average.toString()
        binding.movieGenre.text = movie.genres.take(2).toString()
        Glide.with(binding.imageMovie)
            .load(movie.image?.original)
            .into(binding.imageMovie)
    }
}