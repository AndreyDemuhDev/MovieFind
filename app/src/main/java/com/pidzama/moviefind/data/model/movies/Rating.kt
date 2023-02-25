package com.pidzama.moviefind.data.model.movies

import androidx.room.ColumnInfo

data class Rating(
    @ColumnInfo(name = "average")
    val average: Double
)