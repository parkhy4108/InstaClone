package com.devyoung.search.domain.usecase

data class UseCases(
    val searchUser: SearchUser,
    val getUserInfo: GetUserInfo,
    val getAllPosts: GetAllPosts,
    val checkRequest: CheckRequest,
    val checkFollowingList: CheckFollowingList,
    val sendFollowRequest: SendFollowRequest,
    val deleteFollowRequest: DeleteFollowRequest,
    val getMyAccountEmail: GetMyAccountEmail,
    val updateFollowWaitingList: UpdateFollowWaitingList,
    val updateFollowerNum: UpdateFollowerNum,
    val updateFollowingNum: UpdateFollowingNum,
    val deleteFollowWaitingList: DeleteFollowWaitingList,
    val deleteFollower: DeleteFollower,
    val deleteFollowing: DeleteFollowing
)
