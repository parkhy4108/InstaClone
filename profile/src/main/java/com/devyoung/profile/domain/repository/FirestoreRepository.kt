package com.devyoung.profile.domain.repository

import android.net.Uri
import com.devyoung.profile.data.model.User

interface FirestoreRepository {
    suspend fun getUserInfo(email: String, onError: (Throwable)-> Unit, onSuccess: (User)-> Unit )
    suspend fun getAllPosts(email: String, onError: (Throwable) -> Unit, onSuccess: (List<Uri>) -> Unit)
    suspend fun updateNickName(email: String, oldNickName: String, newNickName: String, onResult: (Throwable?)-> Unit)
    suspend fun updateProfileImg(email: String, image: String, onResult: (Throwable?) -> Unit)
    suspend fun checkNickName(nickName: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)
}