package com.devyoung.profile.domain.usecase

import com.devyoung.profile.domain.repository.FirebaseRepository
import javax.inject.Inject

class UserLogOut @Inject constructor(
    private val repository: FirebaseRepository
){
    suspend operator fun invoke() {
        repository.userLogOut()
    }
}