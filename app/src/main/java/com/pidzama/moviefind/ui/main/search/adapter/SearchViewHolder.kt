package com.pidzama.moviefind.ui.main.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.ItemMovieBinding

class SearchViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.run {
            movieTitle.text = movie.name
            rating.text = movie.rating.average.toString()
            movieGenre.text = movie.genres.take(2).toString()
            Glide.with(itemView)
                .load(movie.image?.original)
                .into(imageMovie)
        }
    }
}