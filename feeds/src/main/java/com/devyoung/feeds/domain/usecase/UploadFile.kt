package com.devyoung.feeds.domain.usecase

import android.net.Uri
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UploadFile @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        myEmail: String,
        postId: String,
        uri: Uri,
        onError: (Throwable) -> Unit,
        onSuccess: (String) -> Unit){
        return repository.uploadFile(myEmail, postId, uri, onError,onSuccess)
    }
}