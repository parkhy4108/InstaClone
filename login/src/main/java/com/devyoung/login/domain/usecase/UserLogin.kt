package com.devyoung.login.domain.usecase

import com.devyoung.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserLogin @Inject constructor(
    private val loginRepository: LoginRepository
){
    suspend operator fun invoke(userName: String, userPassword: String) : FirebaseUser?{
        return loginRepository.userLogin(userName, userPassword)
    }
}
