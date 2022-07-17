package com.devyoung.profile.domain.usecase

data class ProfileUseCases (
    val getUserEmail: GetUserEmail,
    val userLogOut: UserLogOut,
    val getUserInfo: GetUserInfo,
    val getAllPosts: GetAllPosts
)