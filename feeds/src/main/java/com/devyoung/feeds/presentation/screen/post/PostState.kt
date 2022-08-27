package com.devyoung.feeds.presentation.screen.post

import android.net.Uri

data class PostState(
    val imageUrl: Uri? = null,
    val comments: String = "",
    val circleLoading: Boolean = false,
    val openDialog: Boolean = false
    )