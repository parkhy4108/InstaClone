package com.devyoung.profile.domain.repository

import com.devyoung.profile.data.data_source.User


interface FirebaseRepository {
    suspend fun getUserEmail() : String?
    suspend fun userLogOut()


}