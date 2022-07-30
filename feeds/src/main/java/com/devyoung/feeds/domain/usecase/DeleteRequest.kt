package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class DeleteRequest @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(personEmail: String, onResult: (Throwable?) -> Unit) {
        return repository.deleteFollowRequest(personEmail, onResult)
    }
}