package com.pidzama.moviefind.data.model.episodes

data class EpisodesItem(
    val _links: Links,
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: Image?,
    val name: String,
    val number: Int,
    val rating: Rating,
    val runtime: Int,
    val season: Int,
    val summary: String?,
    val type: String,
    val url: String
)

data class Image(
    val medium: String?,
    val original: String?
)

data class Links(
    val self: Self,
    val show: Show
)

data class Rating(
    val average: Double
)

data class Self(
    val href: String
)

data class Show(
    val href: String
)