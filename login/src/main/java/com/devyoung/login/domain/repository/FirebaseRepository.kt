package com.devyoung.login.domain.repository


interface FirebaseRepository {
    fun hasUser(): Boolean
    suspend fun getUserEmail(): String?
    suspend fun userLogin(email:String, password:String, onResult: (Throwable?) -> Unit)
    suspend fun userSignUp(email:String, password: String, onResult: (Throwable?) -> Unit)
}