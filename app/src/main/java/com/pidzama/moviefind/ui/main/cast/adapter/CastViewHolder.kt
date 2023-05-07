package com.pidzama.moviefind.ui.main.cast.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.data.model.cast.CastItem

import com.pidzama.moviefind.databinding.ItemCastBinding

class CastViewHolder(
    private val binding: ItemCastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cast: CastItem) {
        binding.run {
            nameStar.text = cast.person.name
            character.text = cast.character.name
            Glide.with(photoStar)
                .load(cast.person.image?.original)
                .into(photoStar)
        }
    }
}