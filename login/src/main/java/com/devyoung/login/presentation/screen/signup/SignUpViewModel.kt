package com.devyoung.login.presentation.screen.signup

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devyoung.base.PROFILE_SCREEN
import com.devyoung.base.SIGNUP_SCREEN
import com.devyoung.base.snackbar.SnackBarManager
import com.devyoung.login.domain.usecase.UserSignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.devyoung.base.R.string as AppText


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userSignUp: UserSignUp,
) : ViewModel() {

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
        viewModelScope.launch {
            userSignUp(email, password) { exception ->
                if(exception != null) {
                    SnackBarManager.showMessage(AppText.error)
                }else openAndPopUp(PROFILE_SCREEN, SIGNUP_SCREEN)
            }
//            linkWithEmail()
        }

    }

//    fun linkWithEmail(){
//        viewModelScope.launch {
//            linkAccount(email,password) { error ->
//                if(error!=null) SnackBarManager.showMessage(R.string.error)
//            }
//        }
//    }
}

