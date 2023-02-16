package com.pidzama.moviefind.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class AuthorisationRepository {

    private val firebaseAuth: FirebaseAuth = Firebase.auth
    private val userBranchFirebase = FirebaseDatabase.getInstance().getReference("User")
    private var userName = ""
    private var userAge = ""
    private var userCountry = ""
    private var userPhone = ""

    fun isUserLogin(): Boolean {
        return firebaseAuth.currentUser != null
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
        onSuccess: () -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            }
        }
    }

    fun registrationUser(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateUserInfo()
                onSuccess()
            }
        }
    }

    fun updateUserInfo() {
        val uid = firebaseAuth.uid
        val dateRegistration = System.currentTimeMillis()
        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = getEmail()
        hashMap["name"] = userName
        hashMap["age"] = userAge
        hashMap["country"] = userCountry
        hashMap["phone"] = userPhone
        hashMap["imageProfile"] = ""
        hashMap["dateRegistration"] = dateRegistration
        val saveChangesUser = userBranchFirebase
        saveChangesUser.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                it.message
            }
    }

}