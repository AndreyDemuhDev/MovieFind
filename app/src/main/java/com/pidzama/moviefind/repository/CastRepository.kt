package com.pidzama.moviefind.repository

import com.pidzama.moviefind.data.network.ApiService
import javax.inject.Inject

class CastRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCast(id: Int) = apiService.getCast(id)

    suspend fun getPerson(id: Int) = apiService.getInfoPerson(id)

    suspend fun getListMoviePerson(id: Int) = apiService.getListMoviePerson(id)
}