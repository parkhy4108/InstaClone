package com.devyoung.profile.presentation

import android.net.Uri
import com.devyoung.profile.data.data_source.User
import com.google.firebase.storage.StorageReference

data class ProfileState(
    val user: User? = null,
    val posts: ArrayList<String> = arrayListOf(),
    val email: String = "",
    val loading : Boolean = false,
    val view: Boolean = false
    )
