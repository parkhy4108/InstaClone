package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FeedRepository
import javax.inject.Inject

class GetUserId @Inject constructor(
    private val repository: FeedRepository
) {
    operator fun invoke() : String? {
        return repository.getUserId()
    }
}