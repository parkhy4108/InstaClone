package com.devyoung.profile.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen
import com.devyoung.base.SnackbarManager
import com.devyoung.profile.R
import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.domain.usecase.GetAllPosts
import com.devyoung.profile.domain.usecase.GetUserEmail
import com.devyoung.profile.domain.usecase.GetUserInfo
import com.devyoung.profile.domain.usecase.UserLogOut
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userLogOut: UserLogOut,
    private val getUserInfo: GetUserInfo,
    private val getAllPosts: GetAllPosts,
    private val getUserEmail: GetUserEmail

) : InstaViewModel() {

    var profileState = mutableStateOf(ProfileState())
        private set

    var postState = mutableStateListOf<String>()
        private set

    private val email = getUserEmail()

    fun getUserInfo() {
        viewModelScope.launch(exceptionHandler) {
            getUserInfo(email.toString(), ::onError) {
                profileState.value = profileState.value.copy(user = it)
            }
        }
    }

    fun getAllPost() {
        viewModelScope.launch(exceptionHandler) {
            Log.d(TAG, "getAllPost: 시작")
            getAllPosts(email.toString(), ::onError) {
                profileState.value = profileState.value.copy(posts = it)
            }
        }
    }

//    fun getAllPost() {
//        viewModelScope.launch(exceptionHandler) {
//            getAllPosts(::onError) { list ->
//                Log.d(TAG, "getAllPost: $list")
//                list.forEach { uri ->
//                    postState.add(uri)
//                }
//            }
//        }
//    }


    fun userLogOut(restartApp: (String)->Unit) {
        viewModelScope.launch(exceptionHandler) {
            userLogOut()
            restartApp(Screen.First.route)
        }
    }
}