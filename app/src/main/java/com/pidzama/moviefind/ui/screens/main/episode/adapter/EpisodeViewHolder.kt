package com.pidzama.moviefind.ui.screens.main.episode.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.R
import com.pidzama.moviefind.data.model.episodes.EpisodesItem
import com.pidzama.moviefind.databinding.ItemEpisodeBinding

class EpisodeViewHolder(
    private val binding: ItemEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(episode: EpisodesItem) {
        if (episode.image?.medium == null) {
            binding.imageEpisode.setImageResource(R.drawable.ic_no_image)
        } else {
            Glide.with(binding.imageEpisode)
                .load(episode.image.medium)
                .into(binding.imageEpisode)
        }
        binding.nameEpisode.text = episode.name
        binding.episodeNumber.text = episode.number.toString()
        binding.ratingEpisode.text = episode.rating.average.toString()

    }
}