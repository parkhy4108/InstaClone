package com.devyoung.search.domain.usecase

data class UseCases(
    val searchUser: SearchUser,
    val getUserInfo: GetUserInfo,
    val getAllPosts: GetAllPosts,
    val checkRequest: CheckRequest,
    val sendFollowRequest: SendFollowRequest,
    val deleteFollowRequest: DeleteFollowRequest,
    val getMyAccountEmail: GetMyAccountEmail,
    val updateFollowWaitingList: UpdateFollowWaitingList,
    val deleteFollowWaitingList: DeleteFollowWaitingList
)
