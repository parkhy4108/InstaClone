package com.devyoung.feeds.domain.usecase

import android.net.Uri
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class UploadFile @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(uri: Uri, onResult: (Throwable?) -> Unit ){
        return repository.uploadFile(uri, onResult)
    }
}