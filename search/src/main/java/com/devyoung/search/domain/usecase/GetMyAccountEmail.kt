package com.devyoung.search.domain.usecase

import com.devyoung.search.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetMyAccountEmail @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(): String? {
        return repository.getMyAccountEmail()
    }
}