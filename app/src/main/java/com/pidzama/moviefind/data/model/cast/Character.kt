package com.pidzama.moviefind.data.model.cast

import java.io.Serializable

data class Character(
    val _links: Links,
    val id: Int,
    val image: Image,
    val name: String,
    val url: String
)

data class CastItem(
    val character: Character,
    val person: Person,
    val self: Boolean,
    val voice: Boolean
): Serializable

data class Country(
    val code: String,
    val name: String?,
    val timezone: String
)

data class Image(
    val medium: String?,
    val original: String?
)

data class Links(
    val self: Self
)

data class Person(
    val birthday: String?,
    val country: Country?,
    val deathday: Any,
    val gender: String,
    val id: Int,
    val image: Image?,
    val name: String,
    val updated: Int,
    val url: String
)

data class Self(
    val href: String
)