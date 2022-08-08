package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class GetFeed @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(email: String, onError:(Throwable)-> Unit, onSuccess:(List<Post>)->Unit) {
        return repository.getFeed(email, onError, onSuccess)
    }
}