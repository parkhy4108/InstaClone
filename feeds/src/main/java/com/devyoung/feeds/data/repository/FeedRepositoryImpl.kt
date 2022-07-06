package com.devyoung.feeds.data.repository

import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.reposiroty.FeedRepository
import com.google.firebase.auth.ktx.auth
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

    override suspend fun savePost(post: Post, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(post.userId).document("post")
            .collection("postId").document(post.postId)
            .set(post)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updateFeed() {
        TODO("Not yet implemented")
    }

    override fun getUserId(): String? {
        return Firebase.auth.currentUser?.email
    }


}