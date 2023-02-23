package com.pidzama.moviefind.ui.main.cast.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pidzama.moviefind.data.model.cast.CastItem

class CastDiffUtil : DiffUtil.ItemCallback<CastItem>() {
    override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
        return false
    }
}