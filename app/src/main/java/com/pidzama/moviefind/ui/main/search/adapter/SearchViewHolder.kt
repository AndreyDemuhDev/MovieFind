package com.pidzama.moviefind.ui.main.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.data.model.search.SearchItem
import com.pidzama.moviefind.data.model.search.Show
import com.pidzama.moviefind.databinding.ItemMovieBinding

class SearchViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: SearchItem) {
        binding.run {
            movieTitle.text = movie.show.name
            rating.text = movie.show.rating.average.toString()
            movieGenre.text = movie.show.genres.take(2).toString()
            Glide.with(itemView)
                .load(movie.show.image.original)
                .into(imageMovie)
        }
    }
}