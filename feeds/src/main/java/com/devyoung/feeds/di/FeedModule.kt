package com.devyoung.feeds.di

import com.devyoung.feeds.data.repository.FirebaseRepositoryImpl
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import com.devyoung.feeds.domain.usecase.*
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedModule {

    @Provides
    @Singleton
    fun provideFirebaseRepository(): FirebaseRepository = FirebaseRepositoryImpl()


    @Provides
    @Singleton
    fun provideUseCases(firebaseRepository: FirebaseRepository): UseCases {
        return UseCases(
            checkFollowerList = CheckFollowerList(firebaseRepository),
            checkRequest = CheckRequest(firebaseRepository),
            deleteRequest = DeleteRequest(firebaseRepository),
            deleteWaitingList = DeleteWaitingList(firebaseRepository),
            getRequest = GetRequest(firebaseRepository),
            getUserEmail = GetUserEmail(firebaseRepository),
            savePost = SavePost(firebaseRepository),
            sendFollowRequest = SendFollowRequest(firebaseRepository),
            updateFollower = UpdateFollower(firebaseRepository),
            updateFollowing = UpdateFollowing(firebaseRepository),
            updateFollowWaitingList = UpdateFollowWaitingList(firebaseRepository),
            updatePostNum = UpdatePostNum(firebaseRepository),
            uploadFile = UploadFile(firebaseRepository)
        )
    }
}