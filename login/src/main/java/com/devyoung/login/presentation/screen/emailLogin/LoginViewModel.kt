package com.devyoung.login.presentation.screen.emailLogin


import dagger.hilt.android.lifecycle.HiltViewModel
import com.devyoung.login.domain.usecase.UserLogin
import androidx.compose.runtime.mutableStateOf
import com.devyoung.base.R.string as AppText
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.devyoung.base.*

import android.util.Log
import android.util.Patterns
import android.content.ContentValues.TAG
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration

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



    fun onLoginClick(openAndPopUp:(String, String)-> Unit) {

        if (!Patterns.EMAIL_ADDRESS.matcher(loginState.value.userName).matches()) {
            SnackbarManager.showMessage(AppText.emailWrong)
        }
        viewModelScope.launch(exceptionHandler) {
            userLogin(email, password) { error ->
                if(error == null) {
                    openAndPopUp(Screen.Home.route, Screen.Login.route)
                }else {
                    onError(error)
                }
            }
        }
    }

    fun onSignUpClick(openScreen: (String) -> Unit){
        openScreen(Screen.SignUp.route)
    }

}