package com.devyoung.feeds.domain.usecase

import android.net.Uri
import com.devyoung.feeds.domain.reposiroty.FirestoreRepository
import javax.inject.Inject

class UploadFile @Inject constructor(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(userEmail: String, uri: Uri, onResult: (Throwable?) -> Unit ){
        return repository.uploadFile(userEmail,uri, onResult)
    }
}