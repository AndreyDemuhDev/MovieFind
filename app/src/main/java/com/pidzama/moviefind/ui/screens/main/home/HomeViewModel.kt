package com.pidzama.moviefind.ui.screens.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.repository.MovieDataSource
import com.pidzama.moviefind.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDataSource: MovieDataSource
) : ViewModel() {

    var onError: (() -> Unit)? = null

    private val allMovies = MutableLiveData<List<Movie>>()

    val flowHero = Pager(
        PagingConfig(pageSize = 10)
    ) {
        movieDataSource
    }.flow
        .cachedIn(viewModelScope)

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        onError?.invoke()
    }

    fun getAllMovies() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = movieRepository.getAllMovies(1, 30)
            if (response.isSuccessful) {
                allMovies.postValue(response.body())
            } else {
                onError?.invoke()
            }
        }
    }
}