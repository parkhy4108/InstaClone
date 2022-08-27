package com.devyoung.profile.domain.usecase

data class UseCases (
    val getUserEmail: GetUserEmail,
    val userLogOut: UserLogOut,
    val getUserInfo: GetUserInfo,
    val getAllPosts: GetAllPosts,
    val updateNickName: UpdateNickName,
    val updateProfileImg: UpdateProfileImg,
    val checkNickName: CheckNickName
)