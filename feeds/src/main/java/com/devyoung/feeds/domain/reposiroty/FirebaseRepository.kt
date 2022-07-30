package com.devyoung.feeds.domain.reposiroty

import android.net.Uri
import com.devyoung.feeds.data.model.Email
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.User
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    suspend fun getUserEmail(): String?
    suspend fun savePost(post: Post, onResult: (Throwable?) -> Unit)
    suspend fun updatePostNum(onResult: (Throwable?) -> Unit)
    suspend fun uploadFile(uri: Uri, onResult: (Throwable?) -> Unit)
    suspend fun getRequest(onError: (Throwable) -> Unit, onSuccess: (List<User>) -> Unit)
    suspend fun checkRequest(personEmail: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)
    suspend fun deleteFollowRequest(personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun updateFollower(personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun deleteFollowWaitingList(personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun updateFollowing(personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun sendFollowRequest(personEmail: String, onResult: (Throwable?)-> Unit)
    suspend fun updateFollowWaitingList(personEmail: String, onResult: (Throwable?)-> Unit)
    suspend fun checkFollowerList(personEmail: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)

}