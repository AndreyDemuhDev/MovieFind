package com.pidzama.moviefind.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.moviefind.data.model.user.User
import com.pidzama.moviefind.repository.FirebaseAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorisationViewModel : ViewModel() {

    private val authRepository = FirebaseAuthRepository()

    private val _user = MutableLiveData<User>()

    fun signInUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.signInUser(email, password, onSuccess, onError)
        }
    }

    fun getEmail(): String {
        return authRepository.getEmail()
    }

    fun getName(): String =
        authRepository.getUserName()

    fun getAge(): String {
        return authRepository.getUserAge()
    }

    fun getCountry(): String {
        return authRepository.getUserCountry()
    }

    fun getPhone(): String {
        return authRepository.getUserPhone()
    }

    fun registrationUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.registrationUser(email, password, onSuccess, onError)
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.logout()
        }
    }
}
