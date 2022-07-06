package com.devyoung.feeds.di

import com.devyoung.feeds.data.repository.FeedRepositoryImpl
import com.devyoung.feeds.domain.reposiroty.FeedRepository
import com.devyoung.feeds.domain.usecase.FeedUseCases
import com.devyoung.feeds.domain.usecase.GetUserId
import com.devyoung.feeds.domain.usecase.SavePost
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
    fun provideFirebaseRepository(): FeedRepository = FeedRepositoryImpl()

    @Provides
    @Singleton
    fun provideUseCases(repository: FeedRepository): FeedUseCases {
        return FeedUseCases(
            savePost = SavePost(repository),
            getUserId = GetUserId(repository)
        )
    }
}