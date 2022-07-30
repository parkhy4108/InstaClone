package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UpdateFollowWaitingList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        return repository.updateFollowWaitingList(personEmail, onResult)
    }
}