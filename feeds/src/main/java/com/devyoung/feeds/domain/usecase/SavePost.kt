package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.reposiroty.FirestoreRepository
import javax.inject.Inject

class SavePost @Inject constructor(
    private val repository: FirestoreRepository
) {
   suspend operator fun invoke(post: Post, onResult: (Throwable?)-> Unit) {
       return repository.savePost(post,onResult)
   }
}