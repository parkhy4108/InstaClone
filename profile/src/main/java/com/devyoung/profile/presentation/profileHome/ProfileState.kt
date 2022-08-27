package com.devyoung.profile.presentation.profileHome

import android.net.Uri
import com.devyoung.profile.data.model.User

data class ProfileState(
    val user: User? = null,
    val posts: List<Uri> = emptyList(),
    val email: String = "",
    val loading : Boolean = false,
    val view: Boolean = false
    )
