package com.pidzama.moviefind.data.network

import com.pidzama.moviefind.data.model.cast.CastItem
import com.pidzama.moviefind.data.model.cast.Person
import com.pidzama.moviefind.data.model.episodes.EpisodesItem
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.data.model.search.SearchItem
import com.pidzama.moviefind.data.model.search.Show
import com.pidzama.moviefind.data.model.seasons.SeasonsItem

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("shows")
    suspend fun getAllMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int? = 100
    ): Response<List<Movie>>

    @GET("search/shows")
    suspend fun getSearchMovies(
        @Query("q") query: String
    ): Response<ArrayList<SearchItem>>

    @GET("shows/{id}")
    suspend fun getMovie(@Path("id") id: Int): Response<Movie>

    @GET("shows/{id}/seasons")
    suspend fun getSeasons(@Path("id") id: Int): Response<ArrayList<SeasonsItem>>

    @GET("seasons/{id}")
    suspend fun getOneSeason(@Path("id") id: Int): Response<SeasonsItem>

    @GET("shows/{id}/cast")
    suspend fun getCast(@Path("id") id: Int): Response<ArrayList<CastItem>>

    @GET("people/{id}")
    suspend fun getInfoPerson(@Path("id") id: Int): Response<Person>

    @GET("seasons/{id}/episodes")
    suspend fun getAllEpisodes(@Path("id") id: Int): Response<ArrayList<EpisodesItem>>

    @GET("episodes/{id}")
    suspend fun getOneEpisodes(@Path("id") id: Int): Response<EpisodesItem>
}