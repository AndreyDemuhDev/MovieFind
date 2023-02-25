package com.pidzama.moviefind.data.model.movies

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class Network(
    @Embedded(prefix = "country")
    val country: Country,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "officialSite")
    val officialSite: String
)