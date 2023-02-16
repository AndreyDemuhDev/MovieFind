package com.pidzama.moviefind.data.model.cast

import java.io.Serializable

data class CastItem(
    val character: Character,
    val person: Person,
    val self: Boolean,
    val voice: Boolean
): Serializable

