package com.devyoung.login.data.repository

import com.devyoung.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
//    private val firebase: Firebase,
) : LoginRepository {

    override suspend fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    override suspend fun userLogin(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun userSignUp(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

}


//override suspend fun getCurrentUser(): FirebaseUser? {
//    return Firebase.auth.currentUser
//}
//
//override suspend fun getUserId(): String {
//    return firebase.auth.currentUser?.uid.orEmpty()
//}
//
//override suspend fun getUserEmail(): String? {
//    return firebase.auth.currentUser?.email
//}
//
//
//override suspend fun userLogin(email: String, password: String, onResult: (Throwable?) -> Unit) {
//    firebase.auth.signInWithEmailAndPassword(email, password)
//        .addOnCompleteListener {
//            onResult(it.exception)
//        }
//}
//
//override suspend fun userSignUp(email: String, password: String, onResult: (Throwable?) -> Unit) {
//    firebase.auth.createUserWithEmailAndPassword(email,password)
//        .addOnCompleteListener {
//            onResult(it.exception)
//        }
//}
//
//override suspend fun userLogOut() {
//    firebase.auth.signOut()
//}
//
//override suspend fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
//    val credential = EmailAuthProvider.getCredential(email, password)
//
////        firebase.auth.currentUser!!.linkWithCredential(credential)
////            .addOnCompleteListener { onResult(it.exception) }
//}