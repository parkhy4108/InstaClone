package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirestoreRepository
import javax.inject.Inject

class UpdatePostNum @Inject constructor(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(userEmail: String, onResult: (Throwable?)-> Unit) {
        return repository.updatePostNum(userEmail, onResult)
    }
}