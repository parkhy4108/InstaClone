package com.devyoung.profile.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devyoung.base.EMAIL_LOGIN_SCREEN
import com.devyoung.base.InstaViewModel
import com.devyoung.profile.domain.usecase.GetUserEmail
import com.devyoung.profile.domain.usecase.GetUserInfo
import com.devyoung.profile.domain.usecase.UserLogOut
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userLogOut: UserLogOut,
    private val getUserEmail: GetUserEmail,
    private val getUserInfo: GetUserInfo
) : InstaViewModel() {


    var uiState = mutableStateOf(ProfileState())
        private set

    private fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun getUser() {
        viewModelScope.launch(exceptionHandler) {
            getUserInfo(userEmail = getUserEmail.invoke()) { exception ->
                if (exception == null)
            }
        }
    }






    fun userLogOut(restart: (String)->Unit) {
        viewModelScope.launch {
            userLogOut()
//            onEmailChange("")
            restart(EMAIL_LOGIN_SCREEN)
        }
    }


}
