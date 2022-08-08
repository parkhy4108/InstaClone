package com.devyoung.search.domain.repository

import com.devyoung.search.data.Email
import com.devyoung.search.data.User

interface FirebaseRepository {
    fun getMyAccountEmail() : String?
    suspend fun getUserInfo(userEmail: String, onError:(Throwable)->Unit, onSuccess:(User)->Unit)
    suspend fun searchUser(userNickname: String, onError:(Throwable, String)->Unit, onSuccess:(String, String)->Unit)
    suspend fun getAllPosts(userEmail: String, onError: (Throwable) -> Unit, onSuccess: (ArrayList<String>) -> Unit)
    suspend fun checkRequest(email: String, personEmail: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)
    suspend fun checkFollowingList(email: String, personEmail: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)
    suspend fun sendFollowRequest(email: String, personEmail: String, onResult: (Throwable?)-> Unit)
    suspend fun deleteFollowRequest(email: String, personEmail: String,onResult: (Throwable?) -> Unit)
    suspend fun deleteFollowWaitingList(email: String, personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun deleteFollowing(email: String, personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun deleteFollower(email: String, personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun updateFollowWaitingList(email: String, personEmail: String, onResult: (Throwable?)-> Unit)
    suspend fun updateFollowingNum(email: String, onResult: (Throwable?) -> Unit)
    suspend fun updateFollowerNum(email: String, onResult: (Throwable?) -> Unit)
}