package com.pidzama.moviefind.data.model.cast

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