package com.pidzama.moviefind.ui.screens.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.repository.FirebaseDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val databaseRepository: FirebaseDatabaseRepository
) : ViewModel() {

    val movie = MutableLiveData<Movie>()

    var isFavoriteMovie = MutableLiveData(false)

    fun addMovieToFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.addMovieToFavorite(movie)

        }
    }
}