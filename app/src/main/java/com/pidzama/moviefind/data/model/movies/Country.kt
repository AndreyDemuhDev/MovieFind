package com.pidzama.moviefind.data.model.movies

import androidx.room.ColumnInfo

data class Country(
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "timezone")
    val timezone: String
)