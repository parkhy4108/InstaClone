package com.devyoung.profile.data.repository

import android.util.Log
import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.domain.repository.FirebaseRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    override suspend fun getUserEmail(): String {
        var email = Firebase.auth.currentUser?.email
        if (email == null) {
            Log.d("TAG", "getUserEmail: error")
            email = ""
        }
        return email
    }

    override suspend fun userLogOut() {
        Firebase.auth.signOut()
    }

    override suspend fun getFollowerById(userEmail: String): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getFollowingById(userEmail: String): List<User> {
        TODO("Not yet implemented")
    }

}