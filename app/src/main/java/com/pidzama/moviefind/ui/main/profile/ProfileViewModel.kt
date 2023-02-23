package com.pidzama.moviefind.ui.main.profile

import androidx.lifecycle.ViewModel
import com.pidzama.moviefind.repository.FirebaseAuthRepository

class ProfileViewModel : ViewModel() {

    private val authRepository = FirebaseAuthRepository()

    fun getUserName(): String {
        return authRepository.getUserName()
    }

    fun getUserAge(): String {
        return authRepository.getUserAge()
    }
}