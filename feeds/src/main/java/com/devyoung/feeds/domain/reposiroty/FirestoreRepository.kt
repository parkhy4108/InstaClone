package com.devyoung.feeds.domain.reposiroty

import android.net.Uri
import com.devyoung.feeds.data.model.Post
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    suspend fun savePost(post: Post, onResult: (Throwable?) -> Unit)
    suspend fun updatePostNum(userEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun uploadFile(userEmail: String, uri: Uri, onResult: (Throwable?) -> Unit)
    fun getLatestFeed(userId: String, onResult: (Throwable?)->Unit)
    fun getNextFeed()
    fun getPreviousFeed()
    fun updateFeed(userEmail: String) : Flow<List<Post>>
}