package com.pidzama.moviefind.ui.main.cast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pidzama.moviefind.data.model.cast.CastItem
import com.pidzama.moviefind.databinding.ItemCastBinding

class CastAdapter(
    private val onClick: (person: CastItem) -> Unit
) : ListAdapter<CastItem, CastViewHolder>(CastDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
        getItem(position)?.let { person ->
            holder.bind(person)
            holder.itemView.setOnClickListener {
                onClick(person)
            }
        }
    }
}


