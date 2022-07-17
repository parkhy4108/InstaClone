package com.devyoung.profile.presentation

import android.net.Uri
import com.devyoung.profile.data.data_source.Post
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference

data class ProfileState(
    val posts: List<StorageReference> = emptyList(),
    val email: String = "",
    )
