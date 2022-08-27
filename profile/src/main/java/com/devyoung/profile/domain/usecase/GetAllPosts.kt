package com.devyoung.profile.domain.usecase

import android.net.Uri
import com.devyoung.profile.domain.repository.FirestoreRepository
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<Uri>) -> Unit
    ) {
        return repository.getAllPosts(email, onError, onSuccess)
    }
}
