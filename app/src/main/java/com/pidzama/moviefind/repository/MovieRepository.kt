package com.pidzama.moviefind.repository

import com.pidzama.moviefind.data.db.MovieDao
import com.pidzama.moviefind.di.MovieDataBaseModule
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.data.network.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) {

    suspend fun getAllMovies(page: Int, limit: Int) = apiService.getAllMovies(page, limit)

    suspend fun getMovie(id: Int) = apiService.getMovie(id)

    suspend fun getSearchMovie(query: String) = apiService.getSearchMovies(query)

    suspend fun insertMovieToFavorite(movie: Movie) {
        movieDao.addMovieToFavorite(movie)
    }

    suspend fun deleteMovieFromFavorite(movie: Movie) {
        movieDao.deleteMovieFromFavorite(movie)
    }

    suspend fun getListFavoriteMovie(): List<Movie> {
        return movieDao.getAllFavoriteMovie()
    }
}