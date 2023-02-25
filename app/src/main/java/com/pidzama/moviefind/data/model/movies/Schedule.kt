package com.pidzama.moviefind.data.model.movies

import androidx.room.ColumnInfo
import androidx.room.TypeConverters

data class Schedule(
    @ColumnInfo(name = "days")
    @TypeConverters
    val days: List<String>,
    @ColumnInfo(name = "time")
    val time: String
)