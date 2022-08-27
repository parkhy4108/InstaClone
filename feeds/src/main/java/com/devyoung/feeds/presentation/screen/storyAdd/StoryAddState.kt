package com.devyoung.feeds.presentation.screen.storyAdd

import android.net.Uri

data class StoryAddState(
    val imageUrl: Uri? = null,
    val openDialog: Boolean = false,
    val circleLoading: Boolean = false,
    )
