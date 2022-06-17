package com.devyoung.login.domain.usecase

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.devyoung.login.domain.repository.LoginRepository
import javax.inject.Inject

class UserSignUp @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(userName: String, userPassword: String, onResult: (Throwable?)->Unit) {
        return loginRepository.userRegister(userName,userPassword, onResult)
    }

}



