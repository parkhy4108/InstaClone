package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UpdatePostNum @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(myEmail: String, onResult: (Throwable?)-> Unit) {
        return repository.updatePostNum(myEmail, onResult)
    }
}