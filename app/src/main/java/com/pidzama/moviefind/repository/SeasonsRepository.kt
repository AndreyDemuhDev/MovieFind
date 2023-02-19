package com.pidzama.moviefind.repository

import com.pidzama.moviefind.data.model.seasons.SeasonsItem
import com.pidzama.moviefind.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SeasonsRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getOneSeason(id: Int) = apiService.getOneSeason(id)

    suspend fun getAllSeasonsMovie(id: Int): Flow<ArrayList<SeasonsItem>> =
        flow {
            val response = apiService.getSeasons(id)
            if (response.isSuccessful) {
                val list = response.body() ?: arrayListOf()
                emit(list)
            } else {
                emit(arrayListOf())
            }
        }.flowOn(Dispatchers.IO)

}