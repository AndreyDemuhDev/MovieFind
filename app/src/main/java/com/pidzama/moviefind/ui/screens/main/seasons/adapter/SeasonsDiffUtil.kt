package com.pidzama.moviefind.ui.screens.main.seasons.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pidzama.moviefind.data.model.seasons.SeasonsItem

class SeasonsDiffUtil : DiffUtil.ItemCallback<SeasonsItem>() {
    override fun areItemsTheSame(oldItem: SeasonsItem, newItem: SeasonsItem): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: SeasonsItem, newItem: SeasonsItem): Boolean {
        return oldItem == newItem
    }
}