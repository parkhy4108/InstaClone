package com.devyoung.feeds.domain.usecase

data class FeedUseCases(
    val savePost: SavePost,
    val getUserEmail: GetUserEmail,
    val updatePostNum: UpdatePostNum,
    val uploadFile: UploadFile
)
