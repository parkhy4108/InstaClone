package com.devyoung.profile.domain.usecase

import com.devyoung.profile.domain.repository.FirestoreRepository
import javax.inject.Inject

class CheckNickName @Inject constructor(
    private val fireStoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(nickName: String, onError: (Throwable)->Unit, onSuccess: (Boolean)-> Unit)  {
        return fireStoreRepository.checkNickName(nickName, onError, onSuccess)
    }
}