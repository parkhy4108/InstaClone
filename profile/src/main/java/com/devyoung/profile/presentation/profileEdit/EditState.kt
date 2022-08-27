package com.devyoung.profile.presentation.profileEdit

import android.net.Uri

data class EditState(
    val isNickNameExist : Boolean = false,
    val email : String = "",
    val imageUrl: String = "",
    val nickName: String = "",
    val oldNickName: String = "",
    val openDialog: Boolean = false,
    val circleLoading: Boolean = false
)
