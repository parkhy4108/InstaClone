package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class DeleteRequestInMyList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(myEmail: String,personEmail: String, onResult: (Throwable?) -> Unit) {
        return repository.deleteRequestInMyList(myEmail, personEmail, onResult)
    }
}