package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class CheckMyFollowingList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        myEmail: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        return repository.checkMyFollowingList(myEmail,personEmail ,onError, onSuccess)
    }
}