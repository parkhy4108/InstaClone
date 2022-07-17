package com.devyoung.login.di

import com.devyoung.login.data.repository.FirebaseRepositoryImpl
import com.devyoung.login.data.repository.FirestoreRepositoryImpl
import com.devyoung.login.domain.repository.FirebaseRepository
import com.devyoung.login.domain.repository.FirestoreRepository
import com.devyoung.login.domain.usecase.*
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
    fun provideUseCases(firebaseRepository: FirebaseRepository, fireStoreRepository: FirestoreRepository): LoginUseCases{
        return LoginUseCases(
            hasUser = HasUser(firebaseRepository),
            userLogin = UserLogin(firebaseRepository),
            userSignUp = UserSignUp(firebaseRepository),
            saveUserInfo = SaveUserInfo(fireStoreRepository)
        )
    }

}












