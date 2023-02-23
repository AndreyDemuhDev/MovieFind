package com.pidzama.moviefind.ui.main.cast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.cast.Person
import com.pidzama.moviefind.repository.CastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val castRepository: CastRepository
) : ViewModel() {

    val currentPerson = MutableLiveData<Person>()

    fun getInfoPerson(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = castRepository.getPerson(id)
            if (response.isSuccessful) {
                currentPerson.postValue(response.body())
            } else {
                response.errorBody()
            }
        }
    }
}