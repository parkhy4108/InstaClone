package com.devyoung.login.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devyoung.login.domain.repository.LoginRepository
import com.devyoung.login.domain.usecase.UserLogin
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLogin: UserLogin
) : ViewModel() {

    fun loginClick(userName: String, userPassword: String) {
        viewModelScope.launch {
             userLogin(userName,userPassword)
        }
    }
}