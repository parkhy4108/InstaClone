package com.devyoung.search.domain.usecase

import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class CheckRequest @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        email: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        return repository.checkRequest(email, personEmail ,onError, onSuccess)
    }
}