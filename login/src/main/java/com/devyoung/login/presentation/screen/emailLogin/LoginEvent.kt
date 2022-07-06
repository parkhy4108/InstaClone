package com.devyoung.login.presentation.screen.emailLogin

sealed interface LoginEvent{
    data class EmailChange(val value: String): LoginEvent
    data class PasswordChange(val value: String): LoginEvent
    data class LoginClick(val openAndPopUp: (String, String)-> Unit): LoginEvent
    data class SignUpClick(val openScreen: (String)->Unit): LoginEvent
}