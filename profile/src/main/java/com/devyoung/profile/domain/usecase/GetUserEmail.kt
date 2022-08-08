package com.devyoung.profile.domain.usecase

import com.devyoung.profile.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUserEmail @Inject constructor(
    private val repository: FirebaseRepository
){
    operator fun invoke() : String? {
        return repository.getUserEmail()
    }
}