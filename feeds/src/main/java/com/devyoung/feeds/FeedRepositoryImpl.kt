package com.devyoung.feeds

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : FeedRepository {
    override suspend fun getUser(): FirebaseUser? {
        return remoteDataSource.getUser()
    }
}