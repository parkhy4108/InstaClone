package com.devyoung.login.domain.repository

import com.devyoung.login.data.User

interface FirestoreRepository {
    suspend fun saveUserInfo(
        user: User,
        onResult: (Throwable?) -> Unit
    )

}