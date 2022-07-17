package com.devyoung.profile.domain.usecase

import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.domain.repository.FirestoreRepository
import javax.inject.Inject
import kotlin.reflect.KFunction1

class GetUserInfo @Inject constructor(
    private val fireStoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(userEmail: String, onError: (Throwable)->Unit, onSuccess: (User)-> Unit)  {
        return fireStoreRepository.getUserInfo(userEmail, onError, onSuccess )
    }
}