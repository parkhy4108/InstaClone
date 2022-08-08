package com.devyoung.feeds.presentation.screen.feed

import android.graphics.Bitmap
import android.net.Uri
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.User

data class FeedState(
    val email: String = "",
    val hasFollowing: Boolean = false,
    val userList: List<User> = emptyList(),
    val myInfo: User? = null,
    val feedList: List<Post> = emptyList()
)