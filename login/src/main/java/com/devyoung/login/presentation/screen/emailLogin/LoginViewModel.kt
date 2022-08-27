package com.devyoung.login.presentation.screen.emailLogin

import dagger.hilt.android.lifecycle.HiltViewModel
import com.devyoung.login.domain.usecase.UserLogin
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.devyoung.base.*

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

    fun onLoginClick(navigateBottomBar:(String)-> Unit) {
        viewModelScope.launch(exceptionHandler) {
            userLogin(email, password) { error ->
                if(error == null) {
                    navigateBottomBar(BottomBarScreen.Feed.route)
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