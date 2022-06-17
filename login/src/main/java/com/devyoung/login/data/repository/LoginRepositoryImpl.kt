package com.devyoung.login.data.repository

import androidx.compose.runtime.State
import com.devyoung.login.domain.dataSource.RemoteDataSource
import com.devyoung.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource : RemoteDataSource
) : LoginRepository {

//    override suspend fun userLogin(
//        userName: String,
//        userPassword: String,
//        onResult: (Throwable?) -> Unit
//    ) {
//        return remoteDataSource.userLogin(userName, userPassword , onResult)
//    }
//
//    override suspend fun userRegister(
//        userName: String,
//        userPassword: String,
//        onResult: (Throwable?) -> Unit
//    ) {
//        return remoteDataSource.userRegister(userName,userPassword,onResult)
//    }

    override suspend fun userLogin(
        userName: String,
        userPassword: String,
    ) : FirebaseUser? {
        return remoteDataSource.userLogin(userName, userPassword)
    }

    override suspend fun userRegister(
        userName: String,
        userPassword: String,
        onResult: (Throwable?) -> Unit
    ) {
        return remoteDataSource.userRegister(userName,userPassword,onResult)
    }

//    override suspend fun userRegister(
//        userName: String,
//        userPassword: String,
//        onResult:
//    ) {
//        return remoteDataSource.userRegister(userName,userPassword)
//    }

}