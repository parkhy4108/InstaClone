package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.data.model.Story
import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class GetStories @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(email: String ,onError: (Throwable)->Unit,onSuccess: (List<Story>)->Unit, ){
        return repository.getStories(email, onError,onSuccess)
    }
}