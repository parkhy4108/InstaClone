package com.devyoung.profile.domain.repository

import android.net.Uri
import com.devyoung.profile.data.data_source.Post
import com.devyoung.profile.data.data_source.User
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    suspend fun getUserInfo(userEmail: String, onError: (Throwable)-> Unit, onSuccess: (User)-> Unit )
    suspend fun getAllPosts(userEmail: String, onError: (Throwable) -> Unit, onSuccess: (ListResult) -> Unit)
}