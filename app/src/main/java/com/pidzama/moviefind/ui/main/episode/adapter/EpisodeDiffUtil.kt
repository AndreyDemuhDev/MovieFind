package com.pidzama.moviefind.ui.main.episode.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pidzama.moviefind.data.model.episodes.EpisodesItem

class EpisodeDiffUtil : DiffUtil.ItemCallback<EpisodesItem>() {
    override fun areItemsTheSame(oldItem: EpisodesItem, newItem: EpisodesItem): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: EpisodesItem, newItem: EpisodesItem): Boolean {
        return oldItem == newItem
    }
}