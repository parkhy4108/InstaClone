package com.devyoung.profile.domain.usecase

import com.devyoung.profile.domain.repository.FirestoreRepository
import javax.inject.Inject

class UpdateNickName @Inject constructor(
    private val repository : FirestoreRepository
) {
    suspend operator fun invoke(email: String, oldNickName: String, newNickName: String, onResult: (Throwable?)-> Unit)   {
        return repository.updateNickName(email, oldNickName, newNickName, onResult)
    }
}
