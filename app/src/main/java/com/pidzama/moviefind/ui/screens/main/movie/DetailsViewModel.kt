package com.pidzama.moviefind.ui.screens.main.movie

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
}