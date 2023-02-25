package com.pidzama.moviefind.ui.main.home.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.ItemMovieBinding

class MoviesViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.run {
            movieTitle.text = movie.name
            rating.text = movie.rating.average.toString()
            movieGenre.text = movie.genres.take(2).toString()
            Glide.with(imageMovie)
                .load(movie.image?.original)
                .into(imageMovie)
        }
    }
}