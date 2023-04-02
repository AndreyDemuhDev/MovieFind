package com.pidzama.moviefind.ui.main.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.search.SearchItem
import com.pidzama.moviefind.data.model.search.Show
import com.pidzama.moviefind.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    val listSearchMovie = MutableLiveData<ArrayList<SearchItem>>()

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

    fun getSearchMovie(): LiveData<ArrayList<SearchItem>> {
        return listSearchMovie
    }
}