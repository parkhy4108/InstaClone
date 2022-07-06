package com.devyoung.profile.domain.repository

interface ProfileRepository {
    fun getUserEmail() : String?
    suspend fun userLogOut()
}