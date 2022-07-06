package com.devyoung.profile.domain.repository

import com.devyoung.profile.data.data_source.User
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    suspend fun getUserInfo(userEmail: String?, onResult: (Throwable?)-> Unit) : User
}