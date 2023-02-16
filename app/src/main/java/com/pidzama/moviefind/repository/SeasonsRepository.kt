package com.pidzama.moviefind.repository

import com.pidzama.moviefind.data.network.ApiService
import javax.inject.Inject

class SeasonsRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllSeasons(id: Int) = apiService.getSeasons(id)

    suspend fun getOneSeason(id: Int) = apiService.getOneSeason(id)

//    suspend fun getSeasonsFlow(id: Int): Flow<ArrayList<SeasonView>> =
//        flow<ArrayList<SeasonView>> {
//            val response = apiService.getSeasons(id)
//            if (response.isSuccessful) {
//                val list = response.body() ?: arrayListOf()
//                Log.d("A", response.toString())
//                emit(list)
//            } else {
//                emit(arrayListOf())
//            }
//        }.flowOn(Dispatchers.IO)

}