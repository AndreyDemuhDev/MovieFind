package com.pidzama.moviefind.data.model.seasons

import java.io.Serializable

data class Network(
    val country: Country,
    val id: Int,
    val name: String,
    val officialSite: String
)