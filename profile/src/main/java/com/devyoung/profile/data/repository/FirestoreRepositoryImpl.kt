package com.devyoung.profile.data.repository

import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.domain.repository.FirestoreRepository
import com.devyoung.profile.domain.repository.ProfileRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


class FirestoreRepositoryImpl @Inject constructor() : FirestoreRepository {
    override suspend fun getUserInfo(userEmail: String?, onResult: (Throwable?)->Unit): User {
        if (userEmail != null) {
            Firebase.firestore.collection(userEmail).document("userInfo")
                .get()
                .addOnSuccessListener {  document ->
                    if (document != null) {

                    } else {

                    }
                }
        }
    }


}