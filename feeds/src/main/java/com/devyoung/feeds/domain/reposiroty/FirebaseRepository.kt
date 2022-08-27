package com.devyoung.feeds.domain.reposiroty

import android.net.Uri
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.Story
import com.devyoung.feeds.data.model.User

interface FirebaseRepository {

    fun getUserEmail(): String?


    suspend fun checkMyFollowerList(myEmail: String, personEmail: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)
    suspend fun checkMyFollowingList(myEmail: String, personEmail: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)
    suspend fun checkMyWaitingList(myEmail: String,personEmail: String, onError: (Throwable) -> Unit, onSuccess: (Boolean) -> Unit)

    suspend fun deleteMyEmailInUserWaitingList(myEmail: String, personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun deleteRequestInMyList(myEmail: String, personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun deleteRequestInUserList(myEmail: String, personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun deleteUserEmailInMyWaitingList(myEmail: String, personEmail: String, onResult: (Throwable?) -> Unit)

    suspend fun getFeed(email: String, onError: (Throwable) -> Unit, onSuccess: (List<Post>) -> Unit)
    suspend fun getMyInfo(email: String, onError: (Throwable) -> Unit, onSuccess: (User) -> Unit)
    suspend fun getStories(myEmail: String, onError: (Throwable) -> Unit,onSuccess: (List<Story>) -> Unit)

    suspend fun loadMyRequestedList(myEmail: String, onError: (Throwable) -> Unit, onSuccess: (List<User>) -> Unit)

    suspend fun savePost(email: String, post: Post, onResult: (Throwable?) -> Unit)
    suspend fun sendRequestToUser(myEmail: String, personEmail: String, onResult: (Throwable?)-> Unit)
    suspend fun saveStoryImg(email: String, imageUrl: String, date: String ,onResult: (Throwable?) -> Unit)

    suspend fun updateMyFollowerList(myEmail: String, personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun updateMyWaitingList(myEmail: String, personEmail: String, onResult: (Throwable?)-> Unit)
    suspend fun updatePostNum(myEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun updateFollowingNum(email: String, onResult: (Throwable?) -> Unit)
    suspend fun updateFollowerNum(email: String, onResult: (Throwable?) -> Unit)
    suspend fun updateUserFollowingList(myEmail: String, personEmail: String, onResult: (Throwable?) -> Unit)
    suspend fun updateStoryList(email: String, personEmail: String,onResult: (Throwable?) -> Unit)
    suspend fun uploadFile(myEmail: String, postId: String, uri: Uri, onError: (Throwable) -> Unit,onSuccess: (String) -> Unit)


}