package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class SendRequestToUser @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        return repository.sendRequestToUser(myEmail, personEmail,onResult)
    }
}