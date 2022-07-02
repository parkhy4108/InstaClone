package com.devyoung.feeds.data

data class PostData(
    val postId: String,
    val postImage: Int,
    val userName: String,
    val userProfileImage: Int,
    val likes: Int,
    val commentsNum: Int,
    val comments: List<String>
)

