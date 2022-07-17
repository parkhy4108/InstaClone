package com.devyoung.profile.di

import com.devyoung.profile.data.repository.FirestoreRepositoryImpl
import com.devyoung.profile.data.repository.FirebaseRepositoryImpl
import com.devyoung.profile.domain.repository.FirestoreRepository
import com.devyoung.profile.domain.repository.FirebaseRepository
import com.devyoung.profile.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

//    @Provides
//    @Singleton
//    fun provideFirebase() = Firebase

    @Provides
    @Singleton
    fun provideFirebaseRepository(): FirebaseRepository = FirebaseRepositoryImpl()

    @Provides
    @Singleton
    fun provideFirestoreRepository(): FirestoreRepository = FirestoreRepositoryImpl()

    @Provides
    @Singleton
    fun provideUseCases(firebaseRepository: FirebaseRepository, firestoreRepository: FirestoreRepository): ProfileUseCases {
        return ProfileUseCases(
            getUserEmail = GetUserEmail(firebaseRepository),
            userLogOut = UserLogOut(firebaseRepository),
            getUserInfo = GetUserInfo(firestoreRepository),
            getAllPosts = GetAllPosts(firestoreRepository)
        )
    }

}












