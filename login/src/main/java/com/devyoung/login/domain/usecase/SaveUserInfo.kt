package com.devyoung.login.domain.usecase

import com.devyoung.login.data.User
import com.devyoung.login.domain.repository.FirestoreRepository
import javax.inject.Inject

class SaveUserInfo @Inject constructor(
    private val repository: FirestoreRepository
) {
    operator fun invoke(user: User, img: ByteArray,onResult: (Throwable?)-> Unit) {
        repository.saveUserInfo(user, img,onResult)
    }
}