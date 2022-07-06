package com.devyoung.feeds.domain.reposiroty

import com.devyoung.feeds.data.model.Post


interface FeedRepository {
    fun getUserId() : String?
    suspend fun getLatestFeed(userId: String, onResult: (Throwable?)->Unit)
    suspend fun getNextFeed()
    suspend fun getPreviousFeed()
    suspend fun savePost(post: Post, onResult: (Throwable?) -> Unit)
    suspend fun updateFeed()

}