package com.devyoung.profile.domain.repository


interface FirebaseRepository {
    fun getUserEmail() : String?
    suspend fun userLogOut()


}