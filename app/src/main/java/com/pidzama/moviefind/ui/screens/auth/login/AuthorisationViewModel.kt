package com.pidzama.moviefind.ui.screens.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.repository.AuthorisationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorisationViewModel : ViewModel() {

    private val authRepository = AuthorisationRepository()

    fun signInUser(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.signInUser(email, password, onSuccess)
        }
    }

    fun getEmail(): String {
        return authRepository.getEmail()
    }

    fun registrationUser(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.registrationUser(email, password, onSuccess)
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.logout()
        }
    }

    fun createUserInfo(name:String, age: String, country: String, phone: String){

    }
}
