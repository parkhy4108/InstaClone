package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UpdateStoryList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(email: String, personEmail: String,onResult: (Throwable?)-> Unit) {
        return repository.updateStoryList(email,personEmail ,onResult)
    }
}