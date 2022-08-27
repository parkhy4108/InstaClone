package com.devyoung.search.domain.usecase

import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class DeleteFollowing @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        return repository.deleteFollowing(email, personEmail, onResult)
    }
}