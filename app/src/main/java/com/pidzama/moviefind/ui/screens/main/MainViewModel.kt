package com.pidzama.moviefind.ui.screens.main

import androidx.lifecycle.ViewModel
import com.pidzama.moviefind.repository.SharedPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPref: SharedPreferenceRepository
) : ViewModel() {

    fun isFirstStart() = sharedPref.isFirstStart()
}