package com.pidzama.moviefind.ui.main.episode.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pidzama.moviefind.R
import com.pidzama.moviefind.data.model.episodes.EpisodesItem
import com.pidzama.moviefind.databinding.ItemEpisodeBinding

class EpisodeViewHolder(
    private val binding: ItemEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(episode: EpisodesItem) {
        binding.run {
            Glide.with(imageEpisode)
                .load(episode.image?.original ?: R.drawable.ic_no_image)
                .into(imageEpisode)
            nameEpisode.text = episode.name
            episodeNumber.text = episode.number.toString()
            ratingEpisode.text = episode.rating.average.toString()
        }
    }
}