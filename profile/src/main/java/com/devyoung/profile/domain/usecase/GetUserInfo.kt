package com.devyoung.profile.domain.usecase

import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.domain.repository.FirestoreRepository
import javax.inject.Inject

class GetUserInfo @Inject constructor(
    private val fireStoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(onError: (Throwable)->Unit, onSuccess: (User)-> Unit)  {
        return fireStoreRepository.getUserInfo(onError, onSuccess)
    }
}