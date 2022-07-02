package com.devyoung.profile.domain.usecase

import com.devyoung.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UserLogOut @Inject constructor(
    private val repository: ProfileRepository
){
    suspend operator fun invoke() {
        repository.userLogOut()
    }
}