package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UpdateMyFollowerList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(myEmail: String, personEmail: String, onResult: (Throwable?) -> Unit) {
        return repository.updateMyFollowerList(myEmail, personEmail, onResult)
    }
}