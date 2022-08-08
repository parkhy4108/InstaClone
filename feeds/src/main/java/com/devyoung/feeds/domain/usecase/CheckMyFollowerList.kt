package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class CheckMyFollowerList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        myEmail: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        return repository.checkMyFollowerList(myEmail,personEmail ,onError, onSuccess)
    }
}