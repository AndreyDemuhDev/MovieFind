package com.pidzama.moviefind.ui.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val listFavouriteMovie = MutableLiveData<List<Movie>>()

    fun getListFavouriteHero() {
        viewModelScope.launch {
            listFavouriteMovie.value = movieRepository.getListFavoriteMovie()
        }
    }
}
