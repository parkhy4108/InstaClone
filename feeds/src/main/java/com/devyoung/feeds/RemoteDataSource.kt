package com.devyoung.feeds

import com.google.firebase.auth.FirebaseUser

interface RemoteDataSource {
    suspend fun getUser() : FirebaseUser?
}