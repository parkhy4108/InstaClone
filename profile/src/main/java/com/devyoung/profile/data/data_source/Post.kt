package com.devyoung.profile.data.data_source

import android.net.Uri

data class Post(
    val postId: String="",
    val postImg: String = "",
    val userId: String="",
    val date: String="",
    val time: String="",
    val comments: String?="",
    val like: Int = 0,
)

