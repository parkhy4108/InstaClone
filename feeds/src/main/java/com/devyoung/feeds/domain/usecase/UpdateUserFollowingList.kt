package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UpdateUserFollowingList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(myEmail: String, personEmail: String, onResult: (Throwable?) -> Unit) {
        return repository.updateUserFollowingList(myEmail, personEmail, onResult)
    }
}