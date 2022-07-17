package com.devyoung.login.domain.repository


interface FirebaseRepository {
    fun hasUser(): Boolean
    fun userLogin(email:String, password:String, onResult: (Throwable?) -> Unit)
    fun userSignUp(email:String, password: String, onResult: (Throwable?) -> Unit)
}