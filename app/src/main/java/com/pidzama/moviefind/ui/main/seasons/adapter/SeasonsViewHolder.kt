package com.pidzama.moviefind.ui.main.seasons.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.R
import com.pidzama.moviefind.data.model.seasons.SeasonsItem
import com.pidzama.moviefind.databinding.ItemSeasonBinding

class SeasonsViewHolder(
    private val binding: ItemSeasonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(seasons: SeasonsItem) {
        binding.numberSeason.text = seasons.number.toString()
        Glide.with(binding.imageSeason)
            .load(seasons.image?.medium ?: R.drawable.ic_no_image)
            .into(binding.imageSeason)
    }
}