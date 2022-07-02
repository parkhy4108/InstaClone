package com.devyoung.profile.data.repository

import com.devyoung.profile.domain.repository.ProfileRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
//    private val firebase: Firebase,
) : ProfileRepository {

    override suspend fun getUserEmail(): String? {
        return Firebase.auth.currentUser?.email
    }

    override suspend fun userLogOut() {
        Firebase.auth.signOut()
    }

}