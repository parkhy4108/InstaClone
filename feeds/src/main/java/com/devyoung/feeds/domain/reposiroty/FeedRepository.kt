package com.devyoung.feeds.domain.reposiroty


interface FeedRepository {
    suspend fun getLatestFeed(userId: String, onResult: (Throwable?)->Unit)
    suspend fun getNextFeed()
    suspend fun getPreviousFeed()
}