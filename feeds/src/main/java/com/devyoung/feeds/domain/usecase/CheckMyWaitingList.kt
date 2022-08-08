package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class CheckMyWaitingList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        myEmail: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        return repository.checkMyWaitingList(myEmail,personEmail ,onError, onSuccess)
    }
}