package com.pidzama.moviefind.ui.screens.main.movie

import android.util.Log
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seasonsRepository: SeasonsRepository,
    private val castRepository: CastRepository
) : ViewModel() {

    var currentMovie = MutableLiveData<Movie>()

    val listSeasonsOneMovie = MutableLiveData<ArrayList<SeasonsItem>>()

    val currentCast = MutableLiveData<ArrayList<CastItem>>()

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getMovie(id)
            if (response.isSuccessful) {
                currentMovie.postValue(response.body())
                Log.d("AAA", "RESPONSE Movie: ${response.body()}")
            } else {
                response.errorBody()
            }
        }
    }

    fun getAllSeasons(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = seasonsRepository.getAllSeasons(id)
            if (response.isSuccessful) {
                listSeasonsOneMovie.postValue(response.body())
                Log.d("AAA", "RESPONSE Seasons: ${response.body()}")
            } else {
                Log.d("AAA", "RESPONSE Seasons: ${response.errorBody()}")
                response.errorBody()
            }
        }
    }

    fun getCast(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = castRepository.getCast(id)
            if (response.isSuccessful) {
                currentCast.postValue(response.body())
                Log.d("AAA", "RESPONSE Cast: ${response.body()}")
            } else {
                Log.d("AAA", "RESPONSE Cast: ${response.errorBody()}")
                response.errorBody()
            }
        }
    }

//    fun getSeasonsFlow(id: Int){
//        viewModelScope.launch(Dispatchers.IO){
//            seasonsRepository.getSeasonsFlow(id).collect{
//                seasonList.emit(it)
//                Log.d("A",it.toString() )
//            }
//        }
//    }
}