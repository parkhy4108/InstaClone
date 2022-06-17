package com.devyoung.login.presentation.signup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devyoung.base.snackbar.SnackbarManager
import com.devyoung.base.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.devyoung.login.domain.repository.LoginRepository
import com.devyoung.login.domain.usecase.UserSignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
//    private val repository: LoginRepository,
    private val userSignUp: UserSignUp
) : ViewModel() {


    var uiState = mutableStateOf(SignUpState())

    private val userName = uiState.value.userName
    private val userPassword = uiState.value.userPassword

    fun onUserNameChange(newValue: String){
        uiState.value = uiState.value.copy(userName=newValue)
    }

    fun onPasswordChange(newValue: String){
        uiState.value = uiState.value.copy(userPassword = newValue)
    }




//    private val _userName  = mutableStateOf("")
//    private val _userPassword = mutableStateOf("")
//
//    var userName : State<String> = _userName
//    var userPassword  : State<String> = _userPassword


//    fun signUpClick(userName : String, userPassword: String) {
//        viewModelScope.launch {
//            repository.userRegister(userName,userPassword)
//        }
//    }

//    fun signUpClick(userName: String, userPassword: String) {
//        viewModelScope.launch {
//            userSignUp(userName,userPassword)
//        }
//    }

    fun signUpClick() {
        viewModelScope.launch(showErrorHandlerException) {
            userSignUp(userName,userPassword) { error ->
                if(error==null){

                }
            }
        }
    }

    private val showErrorHandlerException = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    private fun onError(error: Throwable){
        SnackbarManager.showMessage(error.toSnackbarMessage())
    }

}

