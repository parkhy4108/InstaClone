package com.devyoung.login.presentation.screen.emailLogin

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devyoung.base.*
import com.devyoung.base.snackbar.SnackBarManager
import com.devyoung.base.snackbar.SnackBarMessage.Companion.toSnackBarMessage
import com.devyoung.base.R.string as AppText
import com.devyoung.login.domain.usecase.UserLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLogin: UserLogin,
//    private val linkAccount: LinkAccount
) : ViewModel() {

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
            SnackBarManager.showMessage(AppText.emailWrong)
        }
        viewModelScope.launch {
            userLogin(email, password) { exception ->
                if(exception != null) {
                    SnackBarManager.showMessage(exception.toSnackBarMessage())
                }else openAndPopUp(PROFILE_SCREEN, EMAIL_LOGIN_SCREEN)
            }
        }
    }

    fun onSignUpClick(openScreen: (String) -> Unit){
        openScreen(SIGNUP_SCREEN)
    }




    fun onEvent(event: LoginEvent) {
        when(event){
            is LoginEvent.EmailChange -> {
                loginState.value = loginState.value.copy(userName = event.value)
            }
            is LoginEvent.PasswordChange -> {
                loginState.value = loginState.value.copy(userPassword = event.value)
            }
            is LoginEvent.LoginClick -> {
                if (!Patterns.EMAIL_ADDRESS.matcher(loginState.value.userName).matches()) {
                    SnackBarManager.showMessage(AppText.emailWrong)
                }
                viewModelScope.launch {
                    userLogin(email, password) { exception ->
                        if(exception != null) {
                            SnackBarManager.showMessage(exception.toSnackBarMessage())
                        }else event.openAndPopUp(PROFILE_SCREEN, EMAIL_LOGIN_SCREEN)
                    }
                }
            }
            is LoginEvent.SignUpClick -> {
                event.openScreen(SIGNUP_SCREEN)
            }
        }
    }

}