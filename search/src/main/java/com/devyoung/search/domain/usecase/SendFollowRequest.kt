package com.devyoung.search.domain.usecase

import com.devyoung.search.data.Email
import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class SendFollowRequest @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        return repository.sendFollowRequest(email, personEmail,onResult)
    }
}