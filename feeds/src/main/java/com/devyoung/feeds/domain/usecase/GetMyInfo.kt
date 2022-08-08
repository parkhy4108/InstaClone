package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class GetMyInfo @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(email: String ,onError: (Throwable)->Unit,onSuccess: (User)->Unit, ){
        return repository.getMyInfo(email, onError,onSuccess)
    }
}