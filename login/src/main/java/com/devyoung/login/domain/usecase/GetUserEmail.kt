package com.devyoung.login.domain.usecase

import com.devyoung.login.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUserEmail @Inject constructor(
    private val repository: FirebaseRepository
){
    suspend operator fun invoke() : String? {
        return repository.getUserEmail()
    }
}