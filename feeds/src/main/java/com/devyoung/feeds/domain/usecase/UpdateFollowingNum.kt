package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UpdateFollowingNum @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(email: String, onResult: (Throwable?)-> Unit) {
        return repository.updateFollowingNum(email, onResult)
    }
}