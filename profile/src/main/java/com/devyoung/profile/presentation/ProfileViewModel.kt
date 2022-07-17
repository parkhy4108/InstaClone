package com.devyoung.profile.presentation

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toFile
import androidx.lifecycle.viewModelScope
import com.devyoung.base.FIRST_SCREEN
import com.devyoung.base.InstaViewModel
import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.domain.usecase.GetAllPosts
import com.devyoung.profile.domain.usecase.GetUserEmail
import com.devyoung.profile.domain.usecase.GetUserInfo
import com.devyoung.profile.domain.usecase.UserLogOut
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userLogOut: UserLogOut,
    private val getUserInfo: GetUserInfo,
    val getUserEmail: GetUserEmail,
    private val getAllPosts: GetAllPosts

) : InstaViewModel() {

    var user = mutableStateOf(User())
        private set
    var profileState = mutableStateOf(ProfileState())
        private set




    fun getAllPosts() : List<StorageReference> {
        val list = mutableStateListOf<StorageReference>()
        viewModelScope.launch(exceptionHandler) {
            val userEmail = getUserEmail()
            Log.d(TAG, "getAllPosts: $userEmail")
            getAllPosts(userEmail, ::onError) { listResult ->
                listResult.items.forEach { item ->
                    list.add(item)
                }
                Log.d(TAG, "getAllPosts: $list")
//                profileState.value = profileState.value.copy(posts = list)
            }
        }
        return list
    }

    fun getUserInfo() {
        viewModelScope.launch(exceptionHandler) {
            val userEmail = getUserEmail()
            getUserInfo(userEmail, ::onError) {
                user.value = user.value.copy(
                    userId = it.userId,
                    userImage = it.userImage,
                    userNickName = it.userNickName,
                    follower = it.follower,
                    following = it.following,
                    postNum = it.postNum
                )
            }
        }
    }

    fun userLogOut(restart: (String)->Unit) {
        viewModelScope.launch(exceptionHandler) {
            userLogOut.invoke()
            restart(FIRST_SCREEN)
        }
    }

}


//    val userEmail = remember { viewModel.getUserEmail() }
//    val storage = Firebase.storage
//    val listRef = storage.reference.child("$userEmail/post/")
//
//    val listAllTask: Task<ListResult> = listRef.listAll()
//    listAllTask.addOnSuccessListener { result ->
//        Log.d(TAG, "loadPost: result ->  $result")
//        val items: List<StorageReference> = result.items
//        Log.d(TAG, "loadPost: items List -> $items")
//        for(item in items){
//            Log.d(TAG, "loadPost: item download -> ${item.downloadUrl}")
//            item.downloadUrl.addOnSuccessListener {
//                posts.add(it)
//            }
//        }
//
//    }


//    fun onPostsChange(newPosts: List<Post>){
//        profileState.value = profileState.value.copy(posts = newPosts)
//    }