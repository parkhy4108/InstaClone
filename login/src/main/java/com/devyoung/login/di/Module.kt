package com.devyoung.login.di

import com.devyoung.login.data.repository.LoginRepositoryImpl
import com.devyoung.login.domain.repository.LoginRepository
import com.devyoung.login.domain.usecase.GetCurrentUser
import com.devyoung.login.domain.usecase.LoginUseCases
import com.devyoung.login.domain.usecase.UserLogin
import com.devyoung.login.domain.usecase.UserSignUp
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
    fun provideFirebaseRepository(): LoginRepository = LoginRepositoryImpl()

    @Provides
    @Singleton
    fun provideUseCases(repository: LoginRepository): LoginUseCases{
        return LoginUseCases(
            getCurrentUser = GetCurrentUser(repository),
            userLogin = UserLogin(repository),
            userSignUp = UserSignUp(repository),
        )
    }

}












