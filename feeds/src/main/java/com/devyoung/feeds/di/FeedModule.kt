package com.devyoung.feeds.di

import com.devyoung.feeds.data.repository.FirebaseRepositoryImpl
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import com.devyoung.feeds.domain.usecase.*
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
            checkMyFollowerList = CheckMyFollowerList(firebaseRepository),
            checkMyWaitingList = CheckMyWaitingList(firebaseRepository),
            deleteRequestInMyList = DeleteRequestInMyList(firebaseRepository),
            deleteMyEmailInUserWaitingList = DeleteMyEmailInUserWaitingList(firebaseRepository),
            loadMyRequestedList = LoadMyRequestedList(firebaseRepository),
            savePost = SavePost(firebaseRepository),
            sendRequestToUser = SendRequestToUser(firebaseRepository),
            updateMyFollowerList = UpdateMyFollowerList(firebaseRepository),
            updateUserFollowingList = UpdateUserFollowingList(firebaseRepository),
            updateMyWaitingList = UpdateMyWaitingList(firebaseRepository),
            updatePostNum = UpdatePostNum(firebaseRepository),
            uploadFile = UploadFile(firebaseRepository),
            getUserEmail = GetUserEmail(firebaseRepository),
            getMyInfo = GetMyInfo(firebaseRepository),
            getFeed = GetFeed(firebaseRepository),
            getStories = GetStories(firebaseRepository),
            deleteUserEmailInMyWaitingList = DeleteUserEmailInMyWaitingList(firebaseRepository),
            deleteRequestInUserList = DeleteRequestInUserList(firebaseRepository),
            updateFollowerNum = UpdateFollowerNum(firebaseRepository),
            updateFollowingNum = UpdateFollowingNum(firebaseRepository),
            saveStoryImg = SaveStoryImg(firebaseRepository)
        )
    }
}
