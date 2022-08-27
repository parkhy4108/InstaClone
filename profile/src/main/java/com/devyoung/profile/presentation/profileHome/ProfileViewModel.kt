package com.devyoung.profile.presentation.profileHome

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen
import com.devyoung.profile.domain.usecase.GetAllPosts
import com.devyoung.profile.domain.usecase.GetUserEmail
import com.devyoung.profile.domain.usecase.GetUserInfo
import com.devyoung.profile.domain.usecase.UserLogOut
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val email = getUserEmail()

    fun getUserInfo() {
        viewModelScope.launch(Dispatchers.Default) {
            getUserInfo(email.toString(), ::onError) {
                profileState.value = profileState.value.copy(user = it)
            }
        }
    }

    fun getAllPost() {
        viewModelScope.launch(Dispatchers.Default) {
            getAllPosts(email.toString(), ::onError) {
                profileState.value = profileState.value.copy(posts = it)
            }
        }
    }

    fun onProfileEditClick(openScreen: (String) -> Unit){
        openScreen(Screen.EditProfile.route)
    }


    fun userLogOut(restartApp: (String)->Unit) {
        viewModelScope.launch(exceptionHandler) {
            userLogOut()
            restartApp(Screen.First.route)
        }
    }
}