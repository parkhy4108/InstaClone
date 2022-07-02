package com.devyoung.login.domain.usecase

import com.devyoung.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val repository: LoginRepository
){
    suspend operator fun invoke() : FirebaseUser? {
        return repository.getCurrentUser()
    }
}