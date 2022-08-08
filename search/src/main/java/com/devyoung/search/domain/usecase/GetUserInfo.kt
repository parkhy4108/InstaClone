package com.devyoung.search.domain.usecase

import com.devyoung.search.data.User
import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUserInfo @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        userEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (User) -> Unit
    ) {
        return repository.getUserInfo(userEmail, onError, onSuccess)
    }
}