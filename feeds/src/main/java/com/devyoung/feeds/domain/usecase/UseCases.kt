package com.devyoung.feeds.domain.usecase

data class UseCases(

    val checkMyFollowerList: CheckMyFollowerList,
    val checkMyWaitingList: CheckMyWaitingList,
    val deleteRequestInMyList: DeleteRequestInMyList,
    val deleteRequestInUserList: DeleteRequestInUserList,
    val deleteMyEmailInUserWaitingList: DeleteMyEmailInUserWaitingList,
    val deleteUserEmailInMyWaitingList: DeleteUserEmailInMyWaitingList,
    val getMyInfo: GetMyInfo,
    val getFeed: GetFeed,
    val getUserEmail: GetUserEmail,
    val loadMyRequestedList: LoadMyRequestedList,
    val savePost: SavePost,
    val sendRequestToUser: SendRequestToUser,
    val updateMyFollowerList: UpdateMyFollowerList,
    val updateMyWaitingList: UpdateMyWaitingList,
    val updateFollowingNum: UpdateFollowingNum,
    val updateFollowerNum: UpdateFollowerNum,
    val updatePostNum: UpdatePostNum,
    val updateUserFollowingList: UpdateUserFollowingList,
    val uploadFile: UploadFile,
    )
