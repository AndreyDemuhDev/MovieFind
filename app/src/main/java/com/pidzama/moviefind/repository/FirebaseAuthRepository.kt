package com.pidzama.moviefind.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.pidzama.moviefind.data.model.user.User
import com.pidzama.moviefind.utils.Constants.Firebase.PATH_USER

class FirebaseAuthRepository {

    private val firebaseAuth: FirebaseAuth = Firebase.auth
    private val userBranchFirebase = FirebaseDatabase.getInstance().getReference(PATH_USER)
    private var userName = ""
    private var userAge = ""
    private var userCountry = ""
    private var userPhone = ""

    fun isUserLogin(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun getUserName(): String {
        return userName
    }

    fun getUserCountry(): String {
        return userCountry
    }

    fun getUserPhone(): String {
        return userPhone
    }

    fun getUserAge(): String {
        return userAge
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    fun getEmail(): String {
        return firebaseAuth.currentUser?.email ?: ""
    }

    fun signInUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                task.exception?.let { onError() }
            }
        }
    }

    fun registrationUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                createUser()
                onSuccess()
            } else {
                task.exception?.let { onError() }
            }
        }
    }

    fun createUser() {
        val uid = firebaseAuth.uid
        val user = User(
            id = uid,
            name = getUserName(),
            age = getUserAge(),
            email = getEmail(),
            country = getUserCountry(),
            phone = getUserPhone(),
            image = ""
        )
        val saveChangesUser = userBranchFirebase
        saveChangesUser.child(uid!!)
            .setValue(user)
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                it.message
            }
    }

}