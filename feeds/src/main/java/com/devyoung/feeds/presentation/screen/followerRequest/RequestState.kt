package com.devyoung.feeds.presentation.screen.followerRequest

import com.devyoung.feeds.data.model.User

data class RequestState (
    val followerList: List<User> = emptyList(),
    val loading: Boolean = false,
    val requestMap: Map<String, Int> = mapOf(),
    val view: Boolean = false
    )