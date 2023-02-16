package com.pidzama.moviefind.ui.screens.main.cast.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.data.model.cast.CastItem

import com.pidzama.moviefind.databinding.ItemCastBinding

class CastViewHolder(
    private val binding: ItemCastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cast: CastItem) {
        binding.nameStar.text = cast.person.name
        binding.character.text = cast.character.name
        Glide.with(binding.photoStar)
            .load(cast.person.image?.medium)
            .into(binding.photoStar)
    }

}