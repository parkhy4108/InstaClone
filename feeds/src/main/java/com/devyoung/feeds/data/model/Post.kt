package com.devyoung.feeds.data.model

data class Post(
    val postId: String = "",
    val postImg: String = "",
    val userId: String = "",
    val userImage: String = "",
    val userNickname: String = "",
    val date: String= "",
    val time: String = "",
    val comments: String?= "",
    val like: Int? = 0,
)