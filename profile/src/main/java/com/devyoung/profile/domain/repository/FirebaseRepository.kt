package com.devyoung.profile.domain.repository

import com.devyoung.profile.data.data_source.User


interface FirebaseRepository {
    suspend fun getUserEmail() : String
    suspend fun userLogOut()
    suspend fun getFollowerById(userEmail: String): List<User>
    suspend fun getFollowingById(userEmail: String): List<User>

}