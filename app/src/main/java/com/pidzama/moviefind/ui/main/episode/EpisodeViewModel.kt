package com.pidzama.moviefind.ui.main.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.episodes.EpisodesItem
import com.pidzama.moviefind.repository.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository
) : ViewModel() {

    val currentOneEpisode = MutableLiveData<EpisodesItem>()

    fun getOneEpisode(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = episodeRepository.getOneEpisode(id)
            if (response.isSuccessful) {
                currentOneEpisode.postValue(response.body())
            } else {
                response.errorBody()
            }
        }
    }
}