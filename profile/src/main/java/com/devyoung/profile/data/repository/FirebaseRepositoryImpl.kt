package com.devyoung.profile.data.repository

import com.devyoung.profile.domain.repository.FirebaseRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    override fun getUserEmail(): String? {
        return Firebase.auth.currentUser?.email
    }

    override suspend fun userLogOut() {
        Firebase.auth.signOut()
    }


}