package com.devyoung.feeds

import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetUser @Inject constructor(
    private val repository: FeedRepository
) {
    suspend operator fun invoke(): FirebaseUser? {
        return repository.getUser()
    }
}