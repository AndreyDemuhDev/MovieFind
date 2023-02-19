package com.pidzama.moviefind.ui.screens.main.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    val listSearchMovie = MutableLiveData<Movie>()

    @SuppressLint("SuspiciousIndentation")
    fun setSearchMovie(query: String) {
        viewModelScope.launch {
            val response = movieRepository.getSearchMovie(query)
            if (response.isSuccessful) {
                listSearchMovie.postValue(response.body())
            } else {
                response.errorBody()
            }
        }
    }

    fun getSearchMovie(): LiveData<Movie> {
        return listSearchMovie
    }

}