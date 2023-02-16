package com.pidzama.moviefind.repository

import com.pidzama.moviefind.data.network.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovies(page: Int, limit: Int) = apiService.getAllMovies(page, limit)

    suspend fun getMovie(id: Int) = apiService.getMovie(id)

    suspend fun getSearchMovie(query: String) =
        apiService.getSearchMovies(query)
}