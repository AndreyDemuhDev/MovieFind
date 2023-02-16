package com.pidzama.moviefind.ui.screens.main.episode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pidzama.moviefind.data.model.episodes.EpisodesItem
import com.pidzama.moviefind.databinding.ItemEpisodeBinding

class EpisodeAdapter(
    private val onClick: (episode: EpisodesItem) -> Unit
) : ListAdapter<EpisodesItem, EpisodeViewHolder>(EpisodeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
        getItem(position)?.let { episode ->
            holder.bind(episode)
            holder.itemView.setOnClickListener {
                onClick(episode)
            }
        }
    }
}