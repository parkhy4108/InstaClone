package com.devyoung.search.di

import com.devyoung.search.data.repository.FirebaseRepositoryImpl
import com.devyoung.search.domain.repository.FirebaseRepository
import com.devyoung.search.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideFirebaseRepository(): FirebaseRepository = FirebaseRepositoryImpl()

    @Provides
    @Singleton
    fun provideUseCases(firebaseRepository: FirebaseRepository): UseCases {
        return UseCases(
            checkRequest = CheckRequest(firebaseRepository),
            searchUser = SearchUser(firebaseRepository),
            getUserInfo = GetUserInfo(firebaseRepository),
            getAllPosts = GetAllPosts(firebaseRepository),
            getMyAccountEmail = GetMyAccountEmail(firebaseRepository),
            sendFollowRequest = SendFollowRequest(firebaseRepository),
            deleteFollowRequest = DeleteFollowRequest(firebaseRepository),
            updateFollowWaitingList = UpdateFollowWaitingList(firebaseRepository),
            deleteFollowWaitingList = DeleteFollowWaitingList(firebaseRepository),
        )
    }

}












