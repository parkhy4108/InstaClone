package com.devyoung.login.domain.dataSource

import androidx.compose.runtime.State
import com.google.firebase.auth.FirebaseUser

interface RemoteDataSource {
    suspend fun userLogin(userName: String, userPassword: String) : FirebaseUser?
    suspend fun userRegister(userName: String, userPassword: String, onResult: (Throwable?)-> Unit)
}