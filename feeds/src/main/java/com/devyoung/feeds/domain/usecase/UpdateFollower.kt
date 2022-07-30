package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.data.model.Email
import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UpdateFollower @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(personEmail: String, onResult: (Throwable?) -> Unit) {
        return repository.updateFollower(personEmail, onResult)
    }
}