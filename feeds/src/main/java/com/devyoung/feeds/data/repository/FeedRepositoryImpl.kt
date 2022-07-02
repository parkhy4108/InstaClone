package com.devyoung.feeds.data.repository

import com.devyoung.feeds.domain.reposiroty.FeedRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor() : FeedRepository {

    override suspend fun getLatestFeed(userId: String, onResult: (Throwable?) -> Unit) {
        Firebase.firestore.collection("Feed")
            .get()
            .addOnSuccessListener { result ->

            }
            .addOnFailureListener { exception ->

            }
    }

    override suspend fun getNextFeed() {
        TODO("Not yet implemented")
    }

    override suspend fun getPreviousFeed() {
        TODO("Not yet implemented")
    }
}