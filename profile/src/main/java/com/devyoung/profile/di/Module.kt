package com.devyoung.profile.di

import com.devyoung.profile.data.repository.ProfileRepositoryImpl
import com.devyoung.profile.domain.repository.ProfileRepository
import com.devyoung.profile.domain.usecase.GetUserEmail
import com.devyoung.profile.domain.usecase.ProfileUseCases
import com.devyoung.profile.domain.usecase.UserLogOut
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
    fun provideFirebaseRepository(): ProfileRepository = ProfileRepositoryImpl()

    @Provides
    @Singleton
    fun provideUseCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            getUserEmail = GetUserEmail(repository),
            userLogOut = UserLogOut(repository)
        )
    }

}












