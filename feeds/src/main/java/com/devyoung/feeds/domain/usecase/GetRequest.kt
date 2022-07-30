package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class GetRequest @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(onError: (Throwable)-> Unit , onSuccess:(List<User>)->Unit) {
        return repository.getRequest(onError, onSuccess)
    }
}