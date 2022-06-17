package com.devyoung.feeds

import com.google.firebase.auth.FirebaseUser

interface FeedRepository {

    suspend fun getUser() : FirebaseUser?

}