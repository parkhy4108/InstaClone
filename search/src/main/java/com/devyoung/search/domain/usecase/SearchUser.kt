package com.devyoung.search.domain.usecase

import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class SearchUser @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        userNickname: String,
        onError: (Throwable, String) -> Unit,
        onSuccess: (String, String) -> Unit
    ) {
        return repository.searchUser(userNickname, onError, onSuccess)
    }
}