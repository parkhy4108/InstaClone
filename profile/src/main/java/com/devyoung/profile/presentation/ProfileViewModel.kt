package com.devyoung.profile.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
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
    private val getAllPosts: GetAllPosts

) : InstaViewModel() {

    var user = mutableStateOf(User())
        private set

    var profileState = mutableStateOf(ProfileState())
        private set

    fun getUserInfo() {
        viewModelScope.launch(exceptionHandler) {
            getUserInfo(::onError) {
                profileState.value = profileState.value.copy(user = it)
            }
        }
    }

    fun getAllPost() {
        viewModelScope.launch(exceptionHandler) {
            getAllPosts(::onError) {
                profileState.value = profileState.value.copy(posts = it)
            }
        }
    }

    fun userLogOut() {
        viewModelScope.launch(exceptionHandler) {
            userLogOut.invoke()
//            restart(FIRST_SCREEN)
        }
    }
}