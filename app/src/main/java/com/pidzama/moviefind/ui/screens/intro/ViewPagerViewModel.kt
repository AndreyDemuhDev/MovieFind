package com.pidzama.moviefind.ui.screens.intro

import androidx.lifecycle.ViewModel
import com.pidzama.moviefind.repository.SharedPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewPagerViewModel @Inject constructor(
    private val sharedPref: SharedPreferenceRepository
) : ViewModel() {

    fun isNoFirstStart() {
        sharedPref.setNoFirstStart()
    }
}