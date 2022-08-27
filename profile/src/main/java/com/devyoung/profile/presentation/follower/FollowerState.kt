package com.devyoung.profile.presentation.follower

import android.net.Uri

data class FollowerState(
    val check : Boolean = false,
    val email : String = "",
    val imageUrl: String = "",
    val nickName: String = "",
    val oldNickName: String = "",
    val openDialog: Boolean = false,
    val isDisplayed: Boolean = false
)
