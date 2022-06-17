package com.devyoung.feeds

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RemoteDataSourceImpl(): RemoteDataSource {
    override suspend fun getUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }
}

