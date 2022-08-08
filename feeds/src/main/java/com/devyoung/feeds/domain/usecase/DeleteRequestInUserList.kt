package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class DeleteRequestInUserList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(myEmail: String,personEmail: String, onResult: (Throwable?) -> Unit) {
        return repository.deleteRequestInUserList(myEmail, personEmail, onResult)
    }
}