package com.pidzama.moviefind.repository

import com.pidzama.moviefind.data.network.ApiService
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllEpisodes(id: Int) = apiService.getAllEpisodes(id)

    suspend fun getOneEpisode(id: Int) = apiService.getOneEpisodes(id)

}