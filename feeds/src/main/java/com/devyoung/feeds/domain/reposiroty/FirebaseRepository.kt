package com.devyoung.feeds.domain.reposiroty

interface FirebaseRepository {
    suspend fun getUserEmail() : String?

}