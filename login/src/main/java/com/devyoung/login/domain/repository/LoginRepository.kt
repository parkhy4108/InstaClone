package com.devyoung.login.domain.repository

import androidx.compose.runtime.State
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {

//    suspend fun userLogin(userName: String, userPassword: String, onResult: (Throwable?) -> Unit)
    //    suspend fun userRegister(userName: String, userPassword: String, onResult: (Throwable?) -> Unit)

    suspend fun userLogin(userName: String, userPassword: String) : FirebaseUser?
    suspend fun userRegister(userName: String, userPassword: String, onResult: (Throwable?)-> Unit)
}