package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class CheckRequest @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        return repository.checkRequest(personEmail ,onError, onSuccess)
    }
}