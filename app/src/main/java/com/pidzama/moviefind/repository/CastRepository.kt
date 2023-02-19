package com.pidzama.moviefind.repository

import com.pidzama.moviefind.data.model.cast.CastItem
import com.pidzama.moviefind.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CastRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPerson(id: Int) = apiService.getInfoPerson(id)

    suspend fun getMovieCast(id: Int): Flow<ArrayList<CastItem>> =
        flow {
            val response = apiService.getCast(id)
            if (response.isSuccessful) {
                val list = response.body() ?: arrayListOf()
                emit(list)
            } else {
                emit(arrayListOf())
            }
        }.flowOn(Dispatchers.IO)
}