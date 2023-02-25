package com.pidzama.moviefind.ui.main.favorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.ItemMovieFullBinding

class FavoriteMovieAdapter(
    private val onClick: (movie: Movie) -> Unit
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    private var listFavouriteMovie = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemMovieFullBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavouriteMovie[position])
        holder.itemView.setOnClickListener {
            onClick(listFavouriteMovie[position])
        }
    }

    override fun getItemCount(): Int {
        return listFavouriteMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListFavouriteMovie(list: List<Movie>) {
        listFavouriteMovie = list
        notifyDataSetChanged()
    }
}
