package com.devyoung.feeds.domain.usecase

data class UseCases(

    val checkFollowerList: CheckFollowerList,
    val checkRequest: CheckRequest,
    val deleteRequest: DeleteRequest,
    val deleteWaitingList: DeleteWaitingList,
    val getRequest: GetRequest,
    val getUserEmail: GetUserEmail,
    val savePost: SavePost,
    val sendFollowRequest: SendFollowRequest,
    val updateFollower: UpdateFollower,
    val updateFollowing: UpdateFollowing,
    val updateFollowWaitingList: UpdateFollowWaitingList,
    val updatePostNum: UpdatePostNum,
    val uploadFile: UploadFile,

)
