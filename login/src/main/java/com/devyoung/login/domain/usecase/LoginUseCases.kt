package com.devyoung.login.domain.usecase

data class LoginUseCases(
    val hasUser: HasUser,
    val userLogin: UserLogin,
    val userSignUp: UserSignUp,
    val saveUserInfo: SaveUserInfo
)