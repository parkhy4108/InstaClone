package com.devyoung.search.domain.usecase

import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        userEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (ArrayList<String>) -> Unit
    ) {
        return repository.getAllPosts(userEmail, onError, onSuccess)
    }
}
