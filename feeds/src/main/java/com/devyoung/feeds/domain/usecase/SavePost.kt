package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class SavePost @Inject constructor(
    private val repository: FirebaseRepository
) {
   suspend operator fun invoke(myEmail: String, post: Post, onResult: (Throwable?)-> Unit) {
       return repository.savePost(myEmail, post,onResult)
   }
}