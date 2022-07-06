package com.devyoung.login.domain.usecase

data class LoginUseCases(
    val getCurrentUser: GetCurrentUser,
    val userLogin: UserLogin,
    val userSignUp: UserSignUp,
    val saveUserInfo: SaveUserInfo
)