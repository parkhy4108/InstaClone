package com.devyoung.profile.domain.usecase

import com.devyoung.profile.domain.repository.FirestoreRepository
import javax.inject.Inject

class UpdateProfileImg @Inject constructor(
    private val repository : FirestoreRepository
) {
    suspend operator fun invoke(email: String, image: String, onResult: (Throwable?) -> Unit) {
        return repository.updateProfileImg(email, image, onResult)
    }
}
