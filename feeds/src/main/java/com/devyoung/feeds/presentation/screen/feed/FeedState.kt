package com.devyoung.feeds.presentation.screen.feed

import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.Story
import com.devyoung.feeds.data.model.User

data class FeedState(
    val email: String = "",
    val hasFollowing: Boolean = false,
    val isDropExpanded: Boolean = false,
    val userList: List<User> = emptyList(),
    val storyList: List<Story> = emptyList(),
    val myInfo: User = User(),
    val myStoryImg: String = "",
    val feedList: List<Post> = emptyList()
)