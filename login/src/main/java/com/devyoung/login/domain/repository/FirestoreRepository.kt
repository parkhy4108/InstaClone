package com.devyoung.login.domain.repository

import com.devyoung.login.data.User

interface FirestoreRepository {
    fun saveUserInfo(user: User, img: ByteArray, onResult: (Throwable?) -> Unit)

}