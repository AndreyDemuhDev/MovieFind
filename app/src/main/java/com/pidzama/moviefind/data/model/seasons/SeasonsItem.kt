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

data class Country(
    val code: String,
    val name: String,
    val timezone: String
)

data class Image(
    var medium: String?,
    val original: String?
)

data class Links(
    val self: Self
)

data class Network(
    val country: Country,
    val id: Int,
    val name: String,
    val officialSite: String
)

data class Self(
    val href: String
)