package com.pidzama.moviefind.ui.main.seasons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pidzama.moviefind.data.model.seasons.SeasonsItem
import com.pidzama.moviefind.databinding.ItemSeasonBinding

class SeasonsAdapter(
    private val onClick: (seasons: SeasonsItem) -> Unit
) : ListAdapter<SeasonsItem, SeasonsViewHolder>(SeasonsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
        return SeasonsViewHolder(
            ItemSeasonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        holder.bind(getItem(position))
        getItem(position)?.let { seasons ->
            holder.bind(seasons)
            holder.itemView.setOnClickListener {
                onClick(seasons)
            }

        }
    }
}