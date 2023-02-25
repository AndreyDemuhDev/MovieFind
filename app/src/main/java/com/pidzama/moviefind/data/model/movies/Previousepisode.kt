package com.pidzama.moviefind.data.model.movies

import androidx.room.ColumnInfo

data class Previousepisode(
    @ColumnInfo(name = "href")
    val href: String
)