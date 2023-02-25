package com.pidzama.moviefind.ui.main.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.ItemMovieFullBinding

class FavoriteViewHolder(
    private val binding: ItemMovieFullBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.run {
            Glide.with(imageMovieItem)
                .load(movie.image?.medium)
                .into(imageMovieItem)
            titleMovieItem.text = movie.name
            genreMovieItem.text = movie.genres.take(2).toString()
            runtimeMovieItem.text = movie.runtime.toString()
            premieredMovieItem.text = movie.premiered
            rating.text = movie.rating.average.toString()
        }
    }
}