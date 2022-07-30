package com.devyoung.search.data

data class User(
    val userEmail: String= "",
    val userImage: String= "",
    val userNickName: String= "",
    val follower: Int = 0,
    val following: Int = 0,
    val postNum : Int = 0
)
