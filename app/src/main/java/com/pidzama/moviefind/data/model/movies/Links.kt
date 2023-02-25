package com.pidzama.moviefind.data.model.movies

import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class Links(
    @Embedded(prefix = "previousepisode")
    val previousepisode: Previousepisode,
    @Embedded(prefix = "self")
    val self: Self
)