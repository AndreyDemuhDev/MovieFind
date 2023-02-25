package com.pidzama.moviefind.data.model.movies

import androidx.room.ColumnInfo

data class Image(
    @ColumnInfo(name = "medium")
    val medium: String?,
    @ColumnInfo(name = "original")
    val original: String?
)