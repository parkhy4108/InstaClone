package com.devyoung.login.domain.repository

import com.google.firebase.auth.FirebaseUser

interface FirebaseRepository {
    suspend fun getCurrentUser(): FirebaseUser?
    suspend fun userLogin(email:String, password:String, onResult: (Throwable?) -> Unit)
    suspend fun userSignUp(email:String, password: String, onResult: (Throwable?) -> Unit)
}