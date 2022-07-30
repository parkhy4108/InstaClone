package com.devyoung.search.domain.repository

import com.devyoung.search.data.Email
import com.devyoung.search.data.User

interface FirebaseRepository {
    suspend fun getMyAccountEmail() : String?
    suspend fun getUserInfo(userEmail: String, onError:(Throwable)->Unit, onSuccess:(User)->Unit)
    suspend fun searchUser(userNickname: String, onError:(Throwable, String)->Unit, onSuccess:(String, String)->Unit)
    suspend fun getAllPosts(userEmail: String, onError: (Throwable) -> Unit, onSuccess: (ArrayList<String>) -> Unit)
    suspend fun checkRequest(personEmail: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)
    suspend fun sendFollowRequest(personEmail: String, onResult: (Throwable?)-> Unit)
    suspend fun deleteFollowRequest(personEmail: String,onResult: (Throwable?) -> Unit)
    suspend fun updateFollowWaitingList(personEmail: String, onResult: (Throwable?)-> Unit)
    suspend fun deleteFollowWaitingList(personEmail: String, onResult: (Throwable?) -> Unit)
}