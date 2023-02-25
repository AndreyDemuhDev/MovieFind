package com.pidzama.moviefind.data.model.movies

import androidx.room.ColumnInfo

data class Self(
    @ColumnInfo(name = "href")
    val href: String
)