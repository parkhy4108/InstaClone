package com.devyoung.feeds.data.repository

import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    override suspend fun getUserEmail(): String? {
        return Firebase.auth.currentUser?.email
    }


}