package com.devyoung.profile.domain.repository

interface ProfileRepository {
    suspend fun getUserEmail() : String?
    suspend fun userLogOut()
}