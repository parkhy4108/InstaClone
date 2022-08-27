package com.devyoung.login.data.repository

import com.devyoung.login.domain.repository.FirebaseRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override suspend fun getUserEmail(): String? {
        return Firebase.auth.currentUser?.email
    }

    override suspend fun userLogin(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun userSignUp(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { onResult(it.exception) }
    }
}