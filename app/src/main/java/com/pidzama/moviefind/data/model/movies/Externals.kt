package com.pidzama.moviefind.data.model.movies

import androidx.room.ColumnInfo

data class Externals(
    @ColumnInfo(name ="imdb")
    val imdb: String,
    @ColumnInfo(name ="thetvdb")
    val thetvdb: Int,
    @ColumnInfo(name ="tvrage")
    val tvrage: Int
)