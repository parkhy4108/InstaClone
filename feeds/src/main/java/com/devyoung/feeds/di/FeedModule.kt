package com.devyoung.feeds.di

import com.devyoung.feeds.data.repository.FirebaseRepositoryImpl
import com.devyoung.feeds.data.repository.FirestoreRepositoryImpl
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import com.devyoung.feeds.domain.reposiroty.FirestoreRepository
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
    fun provideFirestoreRepository(): FirestoreRepository = FirestoreRepositoryImpl()

    @Provides
    @Singleton
    fun provideUseCases(firebaseRepository: FirebaseRepository, firestoreRepository: FirestoreRepository): FeedUseCases {
        return FeedUseCases(
            savePost = SavePost(firestoreRepository),
            getUserEmail = GetUserEmail(firebaseRepository),
            updatePostNum = UpdatePostNum(firestoreRepository),
            uploadFile = UploadFile(firestoreRepository)
        )
    }
}