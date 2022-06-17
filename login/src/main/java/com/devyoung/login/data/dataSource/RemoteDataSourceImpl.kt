package com.devyoung.login.data.dataSource

import androidx.compose.runtime.State
import com.devyoung.login.domain.dataSource.RemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class RemoteDataSourceImpl() : RemoteDataSource {
//    override suspend fun userLogin(
//        userName: String,
//        userPassword: String,
//        onResult: (Throwable?) -> Unit
//    ) {
//        auth.signInWithEmailAndPassword(userName,userPassword)
//            .addOnCompleteListener { onResult(it.exception) }
//    }

//    override suspend fun userRegister(
//        userName: String,
//        userPassword: String,
//        onResult: (Throwable?) -> Unit
//    ) {
//        auth.createUserWithEmailAndPassword(userName,userPassword)
//            .addOnCompleteListener { onResult(it.exception) }
//    }

    override suspend fun userLogin(
        userName: String,
        userPassword: String,
    ) : FirebaseUser? {
        Firebase.auth.signInWithEmailAndPassword(userName,userPassword)
        return Firebase.auth.currentUser
    }

    override suspend fun userRegister(
        userName: String,
        userPassword: String,
        onResult: (Throwable?)->Unit
    ) {
        Firebase.auth.createUserWithEmailAndPassword(userName,userPassword)
            .addOnCompleteListener { onResult(it.exception) }
    }

}
