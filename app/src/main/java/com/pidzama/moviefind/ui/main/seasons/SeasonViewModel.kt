package com.pidzama.moviefind.ui.main.seasons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.episodes.EpisodesItem
import com.pidzama.moviefind.data.model.seasons.SeasonsItem
import com.pidzama.moviefind.repository.EpisodeRepository
import com.pidzama.moviefind.repository.SeasonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(
    private val seasonRepository: SeasonsRepository,
    private val episodeRepository: EpisodeRepository
) : ViewModel() {

    val currentOneSeasonsMovie = MutableLiveData<SeasonsItem>()

    val listAllEpisodesSeason = MutableStateFlow<ArrayList<EpisodesItem>>(arrayListOf())

    fun getOneSeason(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = seasonRepository.getOneSeason(id)
            if (response.isSuccessful) {
                currentOneSeasonsMovie.postValue(response.body())
            } else {
                response.errorBody()
            }
        }
    }

    fun getAllEpisodesMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            episodeRepository.getAllEpisodesSeason(id).collect() {
                listAllEpisodesSeason.emit(it)
            }
        }
    }
}
