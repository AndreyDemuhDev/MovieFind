package com.pidzama.moviefind.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pidzama.moviefind.data.model.movies.Movie
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = movieRepository.getAllMovies(pageNumber, params.loadSize)
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = if (response.body()?.isNotEmpty() == true) pageNumber + 1 else null
            return LoadResult.Page(
                data = response.body() ?: arrayListOf(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}