package com.devyoung.login.presentation.screen.signup

import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.devyoung.base.FEED_SCREEN
import com.devyoung.base.InstaViewModel
import com.devyoung.base.PROFILE_SCREEN
import com.devyoung.base.SIGNUP_SCREEN
import com.devyoung.base.snackbar.SnackBarManager
import com.devyoung.login.data.User
import com.devyoung.login.domain.usecase.SaveUserInfo
import com.devyoung.login.domain.usecase.UserSignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.devyoung.base.R.string as AppText


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userSignUp: UserSignUp,
    private val saveUserInfo: SaveUserInfo
) : InstaViewModel() {

    var signUpState = mutableStateOf(SignUpState())
        private set

    private val email
        get() = signUpState.value.userEmail
    private val nickName
        get() = signUpState.value.userNickName
    private val password
        get() = signUpState.value.userPassword


    fun onEmailChange(newValue: String) {
        signUpState.value = signUpState.value.copy(userEmail = newValue)
    }

    fun onPasswordChange(newValue: String) {
        signUpState.value = signUpState.value.copy(userPassword = newValue)
    }

    fun onNickNameChange(newValue: String) {
        signUpState.value = signUpState.value.copy(userNickName = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit){
        viewModelScope.launch(exceptionHandler) {
            userSignUp(email, password) { exception ->
                if(exception == null) {
                    saveUser(openAndPopUp)
                }else onError(exception)
            }
        }
    }

    private fun saveUser(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            saveUserInfo(
                user = User(
                    userId = email,
                    userImage = "",
                    userNickName = nickName,
                    follower = 0,
                    following = 0,
                    postNum = 0
                )
            ) { exception ->
                if(exception == null){
                    SnackBarManager.showMessage(AppText.signupSuccess)
                    openAndPopUp(PROFILE_SCREEN, SIGNUP_SCREEN)
                }else onError(exception)
            }
        }
    }


}

