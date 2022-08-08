package com.devyoung.search.domain.usecase

import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class UpdateFollowerNum @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(email: String, onResult: (Throwable?)-> Unit) {
        return repository.updateFollowerNum(email, onResult)
    }
}