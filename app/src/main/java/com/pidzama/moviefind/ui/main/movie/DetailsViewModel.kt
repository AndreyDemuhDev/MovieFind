package com.pidzama.moviefind.ui.main.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.cast.CastItem
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.data.model.seasons.SeasonsItem
import com.pidzama.moviefind.repository.CastRepository
import com.pidzama.moviefind.repository.MovieRepository
import com.pidzama.moviefind.repository.SeasonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seasonsRepository: SeasonsRepository,
    private val castRepository: CastRepository
) : ViewModel() {

    var currentMovie = MutableLiveData<Movie>()
    val listSeasonsCurrentMovie = MutableStateFlow<ArrayList<SeasonsItem>>(arrayListOf())
    val listCurrentMovieCast = MutableStateFlow<ArrayList<CastItem>>(arrayListOf())

    var isMovieFavorite = MutableLiveData(false)

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getMovie(id)
            if (response.isSuccessful) {
                currentMovie.postValue(response.body())
            } else {
                response.errorBody()
            }
        }
    }

    fun getSeasonsMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            seasonsRepository.getAllSeasonsMovie(id).collect {
                listSeasonsCurrentMovie.emit(it)
            }
        }
    }

    fun getCastMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            castRepository.getMovieCast(id).collect() {
                listCurrentMovieCast.emit(it)
            }
        }
    }

    fun addMovieToFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movie.isFavorite = true
            movieRepository.insertMovieToFavorite(movie)
        }
    }

    fun deleteMovieFromFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movie.isFavorite = false
            movieRepository.deleteMovieFromFavorite(movie)
        }
    }

    fun chooseMovieFavorite(movie: Movie) {
        if (isMovieFavorite.value == true) {
            deleteMovieFromFavorite(movie)
        } else {
            addMovieToFavorite(movie)
        }
        isMovieFavorite.value = !(isMovieFavorite.value ?: true)
    }
}