package com.devyoung.feeds.data.model

data class Post(
    var postId: String = "",
    var postImg: String = "",
    var userId: String = "",
    var userImage: String = "",
    var userNickName: String = "",
    var date: String= "",
    var time: String = "",
    var comments: String= "",
    var like: String = "",
)