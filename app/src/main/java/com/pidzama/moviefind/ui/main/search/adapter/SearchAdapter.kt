package com.pidzama.moviefind.ui.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.ItemMovieBinding

class SearchAdapter(
    private val onClick: (movie: Movie) -> Unit
) : RecyclerView.Adapter<SearchViewHolder>() {

    private val listMovie = ArrayList<Movie>()

    fun setList(movie: Movie) {
        listMovie.clear()
        listMovie.add(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(listMovie[position])
        holder.itemView.setOnClickListener {
            onClick(listMovie[position])
        }
    }

    override fun getItemCount(): Int = listMovie.size

}