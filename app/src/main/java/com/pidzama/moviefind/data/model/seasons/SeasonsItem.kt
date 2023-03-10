package com.pidzama.moviefind.data.model.seasons

import java.io.Serializable

data class SeasonsItem(
    val _links: Links,
    val endDate: String,
    val episodeOrder: Int,
    var id: Int,
    val image: Image?,
    val name: String,
    val network: Network,
    val number: Int,
    val premiereDate: String,
    val summary: String,
    val url: String,
    val webChannel: Any
): Serializable