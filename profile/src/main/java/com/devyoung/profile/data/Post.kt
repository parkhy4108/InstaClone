package com.devyoung.profile.data

data class Post(
    val postNum: Int = 10,
    val postId: String = "min",
    val postImage: String = "https://randomuser.me/api/portraits/men/15.jpg",
//    val comments: List<String>,
    val like: Int = 140
)
