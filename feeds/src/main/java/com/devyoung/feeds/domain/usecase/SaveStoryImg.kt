package com.devyoung.feeds.domain.usecase

import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import javax.inject.Inject

class SaveStoryImg @Inject constructor(
    private val fireStoreRepository: FirebaseRepository
) {
    suspend operator fun invoke(nickName: String, imageUrl: String , date: String ,onResult: (Throwable?)->Unit)  {
        return fireStoreRepository.saveStoryImg(nickName, imageUrl , date ,onResult)
    }
}