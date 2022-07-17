package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class GetUserEmail @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke() : String? {
        return repository.getUserEmail()
    }
}