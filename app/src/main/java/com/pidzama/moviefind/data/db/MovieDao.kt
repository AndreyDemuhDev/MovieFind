package com.pidzama.moviefind.data.db

import androidx.room.*
import com.pidzama.moviefind.data.model.movies.Movie

@Dao
interface MovieDao {
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addMovieToFavorite(movie: Movie)
//
//    @Delete
//    suspend fun deleteMovieFromFavorite(movie: Movie)
//
//    @Query("SELECT * FROM movie_table")
//    suspend fun getAllFavoriteMovie(): List<Movie>
}