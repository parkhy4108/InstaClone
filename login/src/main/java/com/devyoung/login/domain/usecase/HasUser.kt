package com.devyoung.login.domain.usecase

import com.devyoung.login.domain.repository.FirebaseRepository
import javax.inject.Inject

class HasUser @Inject constructor(
    private val repository: FirebaseRepository
){
    operator fun invoke() : Boolean {
        return repository.hasUser()
    }
}