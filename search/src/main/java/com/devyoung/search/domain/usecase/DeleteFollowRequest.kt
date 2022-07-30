package com.devyoung.search.domain.usecase

import com.devyoung.search.data.Email
import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class DeleteFollowRequest @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        return repository.deleteFollowRequest(personEmail, onResult)
    }
}