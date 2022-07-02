package com.devyoung.profile.domain.usecase

import com.devyoung.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserEmail @Inject constructor(
    private val repository: ProfileRepository
){
    suspend operator fun invoke() : String? {
        return repository.getUserEmail()
    }
}