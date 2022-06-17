package com.devyoung.feeds

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FeedUseCasesModule {
    @Provides
    @Singleton
    fun provideUseCases(
        repository: FeedRepository
    ) : FeedUseCases {
        return FeedUseCases(
            getUser = GetUser(repository)
        )
    }
}