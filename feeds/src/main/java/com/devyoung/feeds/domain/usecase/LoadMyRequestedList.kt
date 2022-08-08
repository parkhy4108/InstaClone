package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class LoadMyRequestedList @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(myEmail: String, onError: (Throwable)-> Unit , onSuccess:(List<User>)->Unit) {
        return repository.loadMyRequestedList(myEmail, onError, onSuccess)
    }
}