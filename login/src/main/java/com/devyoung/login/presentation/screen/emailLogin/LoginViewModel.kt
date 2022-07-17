package com.devyoung.login.presentation.screen.emailLogin


import dagger.hilt.android.lifecycle.HiltViewModel
import com.devyoung.login.domain.usecase.UserLogin
import androidx.compose.runtime.mutableStateOf
import com.devyoung.base.snackbar.SnackBarManager
import com.devyoung.base.R.string as AppText
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.devyoung.base.*

import android.util.Log
import android.util.Patterns
import android.content.ContentValues.TAG

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLogin: UserLogin
) : InstaViewModel() {

    var loginState = mutableStateOf(LoginState())
        private set

    private val email get() = loginState.value.userName
    private val password get() = loginState.value.userPassword

    fun onEmailChange(newValue: String) {
        loginState.value = loginState.value.copy(userName = newValue)
    }

    fun onPasswordChange(newValue: String) {
        loginState.value = loginState.value.copy(userPassword = newValue)
    }



    fun onLoginClick(openAndPopUp:(String)-> Unit) {

        if (!Patterns.EMAIL_ADDRESS.matcher(loginState.value.userName).matches()) {
            SnackBarManager.showMessage(AppText.emailWrong)
        }

        viewModelScope.launch(exceptionHandler) {
            Log.d(TAG, "로그인 버튼 클릭 스코프 처리")
            userLogin(email, password) { exception ->
                if(exception == null) {
//                    openAndPopUp(FEED_SCREEN, LOGIN_SCREEN)
                    openAndPopUp(HOME)
                }else {
                    Log.d(TAG, "스코프 Exception -> $exception")
                    onError(exception)
                }
            }
        }
    }

    fun onSignUpClick(openScreen: (String) -> Unit){
        openScreen(SIGNUP_SCREEN)
    }

}