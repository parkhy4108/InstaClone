package com.devyoung.login.domain.usecase

import com.devyoung.login.domain.repository.LoginRepository
import javax.inject.Inject

class UserSignUp @Inject constructor(
    private val repository: LoginRepository
){
    suspend operator fun invoke(email: String, password: String, onResult: (Throwable?)->Unit) {
        repository.userSignUp(email, password, onResult)
    }
}