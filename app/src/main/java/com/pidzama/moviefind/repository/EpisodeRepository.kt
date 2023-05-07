package com.pidzama.moviefind.repository

import com.pidzama.moviefind.data.model.episodes.EpisodesItem
import com.pidzama.moviefind.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getOneEpisode(id: Int) = apiService.getOneEpisodes(id)

    suspend fun getAllEpisodesSeason(id: Int): Flow<ArrayList<EpisodesItem>> =
        flow {
            val response = apiService.getAllEpisodes(id)
            if (response.isSuccessful) {
                val list = response.body() ?: arrayListOf()
                emit(list)
            } else {
                emit(arrayListOf())
            }
        }.flowOn(Dispatchers.IO)
}