package com.devyoung.login.data

data class User(
    val userId: String,
    val userImage: String,
    val userNickName: String,
    val follower: Int,
    val following: Int,
    val postNum : Int
)
