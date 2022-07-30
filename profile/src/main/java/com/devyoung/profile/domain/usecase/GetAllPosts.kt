package com.devyoung.profile.domain.usecase

import android.net.Uri
import com.devyoung.profile.data.data_source.Post
import com.devyoung.profile.domain.repository.FirestoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository : FirestoreRepository
) {
    suspend operator fun invoke(onError:(Throwable)->Unit, onSuccess: (ArrayList<String>) -> Unit )   {
        return repository.getAllPosts(onError, onSuccess)
    }
}
