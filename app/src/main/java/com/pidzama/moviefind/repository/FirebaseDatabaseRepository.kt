package com.pidzama.moviefind.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.utils.Constants.Firebase.PATH_FAVORITE
import java.util.*

class FirebaseDatabaseRepository {

    private val database = Firebase.database.reference

    fun addMovieToFavorite(movie: Movie) {
        database.child(PATH_FAVORITE).child(Firebase.auth.currentUser?.uid ?: "")
            .child(UUID.randomUUID().toString()).setValue("")
    }
}