package com.devyoung.login.domain.usecase

data class UseCases(
    val hasUser: HasUser,
    val userLogin: UserLogin,
    val userSignUp: UserSignUp,
    val saveUserInfo: SaveUserInfo,
    val getUserEmail: GetUserEmail
)