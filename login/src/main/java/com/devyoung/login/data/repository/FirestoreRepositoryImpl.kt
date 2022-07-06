package com.devyoung.login.data.repository

import com.devyoung.login.data.User
import com.devyoung.login.domain.repository.FirestoreRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor() : FirestoreRepository
{
    override suspend fun saveUserInfo(
        user: User,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(user.userId).document("userInfo")
            .set(user)
            .addOnCompleteListener { onResult(it.exception) }
    }


}